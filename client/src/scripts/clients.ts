import {AsyncQueue, awaitWsReady, ionValueToObject} from "./utils.js";
// @ts-ignore
//import {decode, encode} from "cbor-js/cbor.js";
import {generateKey, sign, readPrivateKey, createCleartextMessage} from 'openpgp/lightweight';
import * as ion from "ion-js";
import PubSub from "pubsub-js";


export type ConnectionStatus = "disconnected" | "connecting" | "connected" | "error";

export class V1ClientConnection {


    readonly incomingQueue = new AsyncQueue<[string, any]>();
    readonly ws: WebSocket
    readonly wsURL: URL

    status: ConnectionStatus = "disconnected";
    private readonly ready: Promise<void>


    constructor(readonly baseURL: string, readonly textMode: boolean = false) {
        let wsURLString = this.urlToWs(baseURL) + "/_zwie/01/socket" + (textMode ? "?text" : "");
        this.wsURL = new URL(wsURLString);

        this.status = "connecting";
        this.ws = new WebSocket(wsURLString);
        this.ws.binaryType = "arraybuffer";
        this.ready = awaitWsReady(this.ws).then(() => {
            this.status = "connected";
        }).catch(() => {
            this.status = "error";
        });
        this.ws.onmessage = (event) => {
            this.handleIncomingMessage(event);
        }
        this.ws.onclose = (event) => {
            console.log("close", event);//TODO: Handle this (reconnect?)
            this.status = "disconnected";
            this.incomingQueue.close();
        }
    }

    private urlToWs(urlString: string) {
        let url = new URL(urlString);
        if (url.protocol === "http:") {
            url.protocol = "ws:";
        } else if (url.protocol === "https:") {
            url.protocol = "wss:";
        } else if (url.protocol === "ws:" || url.protocol === "wss:") {
            // Do nothing as the protocol is already correct
        } else {
            throw new Error("Invalid protocol");
        }
        return url.toString();
    }

    private handleIncomingMessage({data}: MessageEvent) {


        let ionReceived = ion.load(data)
        if (ionReceived !== null) {
            let packet: [string, any];
            packet = ionValueToObject(ionReceived)
            console.debug("client <- server", packet[0], packet[1]);
            this.incomingQueue.push(packet);
        } else {
            this.close(`invalid packet ${ionReceived}`)
        }


        /*
        if (data instanceof ArrayBuffer) {
            //packet = decode(data);
            packet = ion.load(data) as unknown as [string, any];
        } else if (typeof data === "string") {
            //packet = JSON.parse(data);
            packet = ion.load(data) as unknown as [string, any];
        } else {
            this.close("Invalid message type");
            throw new Error("Unexpected message type");
        }
         */

    }


    async awaitReady() {
        return await this.ready;
    }

    async runHandshake() {
        await this.awaitReady();

        console.time(this.baseURL + "::handshake");
        try {
            let key = await generateKey({
                curve: "curve25519",
                type: "ecc",
                userIDs: [{name: "test", email: "test@example.org"}],
                format: "armored",
            })

            console.log(key)

            this.send("client/init/init1", {
                nickname: "test",
                identity: key.publicKey,
            })
            let [type, payload] = await this.receive()
            if (type !== "server/init/init2") {
                console.log("Unexpected message type", type, payload);
                this.close("expected init2");
            }

            let challengeMessage = await createCleartextMessage({
                text: payload.challenge,
            })
            let singed = await sign({
                message: challengeMessage,
                format: "armored",
                signingKeys: await readPrivateKey({armoredKey: key.privateKey}),
            })
            this.send("client/init/init3", {response: singed,})
            if ((await this.receive())[0] !== "server/init/init4") {
                this.close("expected init3");
            }
        } catch (e) {
            console.error(e)
            this.close()
        } finally {
            console.timeEnd(this.baseURL + "::handshake");
        }

    }

    async receive(): Promise<[string, any]> {
        return this.incomingQueue.pop();
    }

    send(type: string, packet: any) {
        console.debug("client -> server", type, packet);
        let finalPacket = [type, packet];
        let packetData: string | ArrayBuffer;

        if (this.textMode) {
            //this.ws.send(JSON.stringify(finalPacket));
            packetData = ion.dumpText(finalPacket)
        } else {
            packetData = ion.dumpBinary(finalPacket)
            //this.ws.send(encode(finalPacket));
        }
        this.ws.send(packetData)
    }

    async runLoop() {
        let setupEvent = new ConnectionHandlerSetup(this)
        PubSub.publish("network.01.setup",setupEvent)
        await this.awaitReady();

        let packetHandlers = setupEvent.packetHandlers;
        let packetHandlerNames = Object.keys(packetHandlers);

        let anyPacketHandlers = setupEvent.anyPacketHandlers;

        while (this.status === "connected") {
            let [type, payload] = await this.receive()
            let handlers = packetHandlers.get(type)
            let specHandlersPromise: Promise<Awaited<void>[]> | undefined = undefined
            if (handlers) {
                specHandlersPromise = Promise.all(
                    handlers.map(handler => async () => {
                        await handler(payload)
                    }).map((fun) => fun())
                )
            }
            await Promise.all(anyPacketHandlers.map((handler) => async () => {
                handler(type, payload)
            }).map((fun) => fun()))
            if (specHandlersPromise)
                await specHandlersPromise
        }

    }

    close(reason?: string) {
        //console.log("close websocket", reason);
        this.ws.close(1000, reason);
    }
}

export class ConnectionHandlerSetup {
    packetHandlers = new Map<string, ((packet: any) => void)[]>();
    anyPacketHandlers: ((type: string, packet: any) => void)[] = [];

    constructor(readonly connection: V1ClientConnection) {

    }

    anyHandler(handler: (type: string, payload: any) => void) {
        this.anyPacketHandlers.push(handler);
    }

    handler(packetType: string, handler: (packet: any) => void) {
        if (!this.packetHandlers.has(packetType)) {
            this.packetHandlers.set(packetType, []);
        }
        this.packetHandlers.get(packetType)!.push(handler);
    }

}

PubSub.subscribe("network.01.setup", (msg, setup: ConnectionHandlerSetup) => {
    let connection = setup.connection;
    setup.handler("ping", (packet: any) => {
        connection.send("pong", {sequence: packet.sequence})
    })
})