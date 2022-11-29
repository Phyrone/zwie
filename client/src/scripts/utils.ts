import type {dom} from "ion-js"
import {IonType, IonTypes} from "ion-js";

export async function awaitWsReady(websocket: WebSocket) {
    return new Promise<void>((resolve, reject) => {
        websocket.onopen = () => {
            resolve();
        }
        websocket.onerror = (e) => {
            reject(e)
        }
    });

}

export class AsyncQueue<T> {
    private queue: T[] = [];
    private waiting: [((value: T) => void), ((reason?: any) => void)][] = [];

    public push(value: T) {
        let pair = this.waiting.shift();
        if (pair) {
            pair[0](value);
        } else {
            this.queue.push(value)
        }
    }

    public async pop(): Promise<T> {
        let v = this.queue.shift();
        if (v) return Promise.reject(v)
        else
            return new Promise((resolve, reject) => {
                this.waiting.push([resolve, reject])
            })
    }

    public close() {
        this.waiting.forEach(([_, reject]) => {
            reject("Queue closed")
        })
        this.waiting = [];
        this.queue = [];
    }
}

export function ionValueToObject(value: dom.Value): any | null {
    switch (value.getType()) {
        case IonTypes.NULL:
            return null;
        case IonTypes.BOOL:
            return value.booleanValue();
        case IonTypes.INT:
            return value.numberValue();
        case IonTypes.FLOAT:
            return value.numberValue();
        case IonTypes.DECIMAL:
            return value.decimalValue();
        case IonTypes.STRING:
            return value.stringValue();
        case IonTypes.SYMBOL:
            return value.stringValue();
        case IonTypes.TIMESTAMP:
            return value.timestampValue();
        case IonTypes.CLOB:
            return value.stringValue();
        case IonTypes.BLOB:
            return value.uInt8ArrayValue();
        case IonTypes.LIST:
            return value.elements().map(ionValueToObject);
        case IonTypes.STRUCT:
            let obj: any = {};
            for (let [fieldName, fieldValue] of value.fields()) {
                obj[fieldName] = ionValueToObject(fieldValue);
            }
            return obj;
        default:
            throw new Error("Unknown IonType: " + value.getType());
    }
}