import localforage from "localforage";
import {writable} from "svelte/store";
import {V1ClientConnection} from "./client-connection.js";
import {singleValue} from "./minor/utils.js";
import type {ProfileData} from "./profile.js";


export const activeClients = writable<Client[]>([])
export let activeClientsNow: Client[] = []
activeClients.subscribe((clients) => {
    activeClientsNow = clients;
})

export async function loadFromDatabase() {
    await localforage.ready()
   await localforage.getItem("zwie::clients")

}
type ClientConfig={
    name:string,
    url:string,


}

export class Client {

    connection = writable<V1ClientConnection | undefined>(undefined)

    constructor(readonly name: string,
                readonly baseURL: string,
                readonly profile: ProfileData,
    ) {

    }

    async connect() {
        await this.disconnect()
        let newConnection = new V1ClientConnection(this.baseURL, this.profile, true)
        await newConnection.runHandshake()
        this.connection.set(newConnection)
        newConnection.runLoop().then()
    }

    async disconnect() {
        let connection = await singleValue(this.connection)
        if (connection) {
            await connection.close()
        }
        this.connection.set(undefined)
    }


}