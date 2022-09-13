import Swal from 'sweetalert2'
import {DispatchedAlert, sendAlert} from "./messages.js";


//TODO optimize this

let registration: ServiceWorkerRegistration;

function inform_update_availible() {
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
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.addEventListener('controllerchange', () => {
            console.log("Controllerchange event -> reloading")
            Swal.fire({
                title: 'Restarting',
                text: 'Please Wait'
            })
            Swal.showLoading()
            window.location.reload()
        })
        const c_registration = await navigator.serviceWorker.getRegistration()
        if (c_registration) {
            registration = c_registration;
            if (registration.waiting || registration.installing)
                inform_update_availible()
            else
                registration.addEventListener('updatefound', inform_update_availible)
            //look for updates every 60 minutes (or on reload)
            setInterval(() => c_registration.update(), 1000 * 60 * 60)
        }
    }
}