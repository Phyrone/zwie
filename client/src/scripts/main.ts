import {relaunch, exit} from "@tauri-apps/api/process";
import Swal from "sweetalert2";



//import App from '../components/App.svelte';
window["global"] = window;

async function main() {
    console.log("main");
    try {
        console.time("init-app-view")
        let App = (await import("../components/App.svelte")).default
        document.body.innerHTML = ''
        window['app'] = new App({
            target: document.body
        })
        console.timeEnd("init-app-view")
    } catch (e) {
        console.timeEnd("init-app-view")


        console.error(e)
        let message = e.message ?? e ?? 'Unknown error';
        let result = await Swal.fire({
            title: 'Error',
            text: message,
            confirmButtonText: "Restart",
            cancelButtonText: "Exit",
            showCancelButton: true,
            allowOutsideClick: false,
            allowEnterKey: true,
        })

        if (result.isConfirmed) {
            relaunch().then();
        } else {
            exit(1).then();
        }
    }
}

main();


export default {}
