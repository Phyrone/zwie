import Gun, {} from "gun";
import * as localforage from "localforage";
import type {MatrixClient} from "matrix-js-sdk"
import "gun/axe"
import "gun/sea"

import {createClient,} from "matrix-js-sdk"
import {writable} from "svelte/store";
import type {Writable} from "svelte/store";

/*
let matrixClient = createClient({
    baseUrl: "https://matrix.org",
})
if (debug) {
    // @ts-ignore
    window["matrix"] = matrixClient
}

 */

/*
let gun = new Gun({

})
*/


type MatrixLoginData = {
    user_id: string,
    accessToken: string,
}

export function addClient(){

}

class PmClient {
    readonly userID: Writable<string> = writable<string>("")

    private matrixClient: MatrixClient | null = null


    async login(username: string, password: string) {

    }

    async resume() {

    }


}

class PmRoom {
    constructor(readonly client: PmClient) {
    }
}


const matrixSessionPrefix = "pm::matrix::session::"

export class MatrixClientW {
    client: MatrixClient

    constructor(
        readonly id: string,
        baseUrl: string,
        userID?: string,
        token?: string,
    ) {

        this.client = createClient({
            baseUrl,
            accessToken: token,
            userId: userID,

        })
    }

    async login(username: string, password: string) {
        console.log("login...", username)
        await this.client.loginWithPassword(username, password)
    }

    async resumeLogin() {

        let loginData = await localforage.getItem<MatrixLoginData>(matrixSessionPrefix + this.id)
        console.log("resumeLogin", loginData)
        if (loginData) {
            this.client.credentials.userId = loginData.user_id
            await this.client.setAccessToken(loginData.accessToken)
            //await this.client.loginWithToken(token)
        } else {
            throw "no token found"
        }
    }

    async canResumeLogin(): Promise<boolean> {
        return await localforage.getItem<MatrixLoginData>(matrixSessionPrefix + this.id) !== null
    }

    async saveLogin() {
        console.log("save login...", this.client.getAccessToken())
        let userID = this.client.getUserId()
        let loginToken = this.client.getAccessToken()
        if (userID && loginToken)
            await localforage.setItem<MatrixLoginData>(matrixSessionPrefix + this.id, {
                accessToken: loginToken,
                user_id: userID,
            })

    }

    async start() {

        await this.client.startClient()
        this.client.getAccessToken()
    }

    async stop() {
        await this.client.logout()
        this.client.stopClient()
    }

}
