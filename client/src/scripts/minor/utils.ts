import type {dom} from "ion-js"
import {IonTypes} from "ion-js";
import type {Readable} from "svelte/store";


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
            let annotations = value.getAnnotations()
            let obj: any = {};
            for (let [fieldName, fieldValue] of value.fields()) {
                obj[fieldName] = ionValueToObject(fieldValue);
            }
            if (annotations.length > 0) {
                return [...annotations, obj];
            } else
                return obj;
        default:
            throw new Error("Unknown IonType: " + value.getType());
    }
}

export function singleValue<T>(writable: Readable<T>): Promise<T> {
    return new Promise((resolve, reject) => {
        writable.subscribe((v) => {
            resolve(v)
        })();
    });
}