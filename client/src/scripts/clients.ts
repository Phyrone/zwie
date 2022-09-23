import SockJS from "sockjs-client";

export class ClientConnection {

    ws: WebSocket

    constructor(readonly url: string) {
        this.ws = new SockJS(url + "/api/v1/socket", {
            sessionId: 32
        }) as WebSocket;
    }


}