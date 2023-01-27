import {de, zwie} from "@client/backend/backend";
import PlatformInfo = de.phyrone.zwie.client.backend.PlatformInfo;
import {dev} from "$app/environment";
import ClientPlatform = de.phyrone.zwie.client.backend.ClientPlatform;

type ZwieJsClientBackend = de.phyrone.zwie.client.backend.ZwieJsClientBackend;

export let backend: ZwieJsClientBackend = zwie.createBackend(obtainPlatformInfo())

window.onbeforeunload = () => {
    backend.destroy()
}

function obtainPlatformInfo(): PlatformInfo {
    return new PlatformInfo(dev, obtainClientPlatform())
}

function obtainClientPlatform(): ClientPlatform {
    if ('__TAURI__' in window) {
        return ClientPlatform.TAURI
    } else {
        return ClientPlatform.WEB
    }
}