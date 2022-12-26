import {writable} from "svelte/store";
import Swal from 'sweetalert2'
import {sendAlert} from "./alerts.js";
import {dev} from '$app/environment';
import {platform} from "./platform.js";


//TODO optimize this

let registration: ServiceWorkerRegistration;

function inform_update_available() {
    if (registration.active)
        sendAlert({
            text: "Update installed Click here to Restart",
            type: "info",
            showCloseButton: false,
            onClick: async (alert) => {
                alert.text.set('Installing Please wait...')
                await apply_update()
            }
        })
}

async function apply_update() {
    await registration.update()
    if (registration.installing) {
        console.log("SW still installing waiting for it to finish")
        registration.installing.addEventListener('statechange', apply_update_stage_2)
    } else
        await apply_update_stage_2()
}

async function apply_update_stage_2() {
    console.log("SW installed apply update to Application")
    if (registration.waiting)
        registration.waiting.postMessage('APPLY_UPDATE')
    else
        window.location.reload()
}


export async function init() {

    if (platform === "tauri") {
        console.log("Service Worker skipped! (Tauri)")
        return
    } else if (dev) {
        console.log("Service Worker skipped! (dev mode)")
        return
    }


    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.addEventListener('controllerchange', () => {
            console.log("Controllerchange event -> reloading")
            Swal.fire({
                title: 'Restarting',
                text: 'Please Wait'
            })
            Swal.showLoading(null)
            window.location.reload()
        })
        const c_registration = await navigator.serviceWorker.register("/service-worker.js")
        if (c_registration) {
            registration = c_registration;

            if (registration.waiting || registration.installing)
                inform_update_available()
            else
                registration.addEventListener('updatefound', inform_update_available)
            //look for updates every 60 minutes
            setInterval(() => c_registration.update(), 1000 * 60 * 60)
        }
    } else {
        console.log("Service Worker not supported by browser")
    }
}

export const installPromt = writable<Event | null>()

window.addEventListener('beforeinstallprompt', (event) => {
    event.preventDefault();
    installPromt.set(event)
})