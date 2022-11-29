<script lang="ts">


    //import Swal from "sweetalert2";
    import {V1ClientConnection} from "../../scripts/clients.js";
    //import {sendAlert} from "../../scripts/messages.js";

    //let counter = 1;


    async function abcde() {
        console.log("abcde");
        let connection = new V1ClientConnection("http://localhost:4342/", false)
        await connection.runHandshake()
        await connection.runLoop();
    }
</script>

<h1>Welcome to Zwie</h1>
<h2>dev-0.0.10</h2>
<button class="btn btn-outline" on:click={abcde}>Connect</button>

<!--
<button class="btn" on:click={()=>sendAlert({
    text: "Normal Alert "+counter++,
    onClick: ()=>Swal.fire({
        text: "Click",
    })
})}>Normal Alert
</button>
<button class="btn btn-info" on:click={()=>sendAlert({
    text: "Info Alert "+counter++,
    type: "info",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "info"
    })
})}>Info Alert
</button>

<button class="btn btn-warning" on:click={()=>sendAlert({
    text: "Warning Alert "+counter++,
    type: "warning",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "warning"
    })
})}>Warning Alert
</button>

<button class="btn btn-error" on:click={()=>sendAlert({
    text: "Error Alert "+counter++,
    type: "error",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "error"
    })
})}>Error Alert
</button>
-->