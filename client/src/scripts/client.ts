import EventBus from "@vertx/eventbus-bridge-client.js";
import type {Writable} from "svelte/store";
import {de} from "zwie-shared";
import type {uuid} from "./types.js";
import WebSocketWrapper = de.phyrone.zwie.shared.js.WebSocketWrapper;
import PacketServerConnectionResponse = de.phyrone.zwie.shared.PacketServerConnectionResponse;
import PacketServerConnectionDenied = de.phyrone.zwie.shared.PacketServerConnectionDenied;

type ZPacket = de.phyrone.zwie.shared.ZPacket;

export type ClientConnectionState = "disconnected" | "connecting" | "connected"

export class ClientConnection {
    wrapper: WebSocketWrapper;
    uuid: uuid;

    state : Writable<ClientConnectionState>

    constructor(readonly webSocket: WebSocket) {
        this.state.set("disconnected");
        this.wrapper = new WebSocketWrapper(webSocket);
    }

    async receiver() {
        this.state.set("connecting");
        await this.handshake()
        this.state.set("connected");
        while (true) {
            try {
                let packet = await this.wrapper.receive()
                try {
                    await this.handlePacket(packet)
                } catch (e) {
                    console.error(e)
                }
            } catch (e) {
                break;
            }
        }
        this.state.set("disconnected");
    }

    async handlePacket(packet: ZPacket) {

    }

    async handshake() {
        let response = await this.wrapper.receive()
        if (response instanceof PacketServerConnectionResponse) {
            response.uuid;
        } else if (response instanceof PacketServerConnectionDenied) {
            response.reason;
        }
    }
}

let ev = new EventBus("");
ev.enablePing(true)
ev.enableReconnect(true)

