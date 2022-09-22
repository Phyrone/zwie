export type Platform = "web" | "tauri" //TODO some android/ios stuff

function getPlatform(): Platform {
    if ('__TAURI__' in window) {
        return "tauri"
    } else {
        return "web"
    }
}

export const platform: Platform = getPlatform();