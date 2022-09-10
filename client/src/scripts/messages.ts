import {writable} from "svelte/store";
import type {Writable} from "svelte/store";


export type AlertType = "info" | "warning" | "error" | "none"

export const alerts = writable<DispatchedAlert[]>([])

export function sendAlert(options: ZwieAlertOptions): DispatchedAlert {

    const alert = new DispatchedAlert(options)
    alerts.update((alerts) => [alert, ...alerts])
    return alert
}


export class DispatchedAlert {

    //TODO callback buttons
    //TODO timeout

    constructor(options: ZwieAlertOptions) {
        this.text = writable<string>(options.text)
        this.type = writable<AlertType>(options.type ?? "none")
        this.closeable = writable<boolean>(options.showCloseButton ?? true)
        this.closeOnClick = options.closeOnClick ?? false

        this.onAlertClose = options.onClose
        this.onAlertClick = options.onClick
    }

    onAlertClose?: (alert: DispatchedAlert) => void
    onAlertClick?: (alert: DispatchedAlert) => void
    public text: Writable<string>
    public type: Writable<AlertType>
    public closeable: Writable<boolean>
    closeOnClick: boolean

    public click() {
        if (this.onAlertClick)
            this.onAlertClick(this)

        if (this.closeOnClick)
            this.close()
    }

    public close() {
        console.log("close", this)
        alerts.update((alerts) => alerts.filter((alert) => alert !== this))
        if (this.onAlertClose)
            this.onAlertClose(this)

    }


}

export interface ZwieAlertOptions {
    type?: AlertType
    text: string,
    showCloseButton?: boolean
    closeOnClick?: boolean
    timeout?: number,
    onClick?: (alert: DispatchedAlert) => void
    onClose?: (alert: DispatchedAlert) => void
}