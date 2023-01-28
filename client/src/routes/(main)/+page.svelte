<script lang="ts">
    import Swal from "sweetalert2";
    import {sendAlert} from "../../scripts/minor/alerts";

    let counter = 1;
</script>
<!--
<script lang="ts">


    import ContextMenuWrapper from "$lib/ContextMenuListener.svelte";
    import {window} from "@tauri-apps/api";
    import {Buffer} from "buffer";
    import {WebsocketClientTransport} from "rsocket-websocket-client";
    import {RSocketConnector,RSocket} from "rsocket-core";

    //import Swal from "sweetalert2";
    import {V1ClientConnection} from "../../scripts/client-connection.js";
    import {singleValue} from "../../scripts/minor/utils.js";
    import {createDefaultProfileData} from "../../scripts/profile.js";
    import ConnectionStatusBox from "./ConnectionStatusBox.svelte";
    import {sendAlert} from "../../scripts/minor/alerts.js";

    //let counter = 1;


    let connections: V1ClientConnection[] = [];

    async function abcde() {
        console.log("abcde");
        let connection = new V1ClientConnection(url, await createDefaultProfileData("client"), textMode)
        connections = [...connections, connection];
        await connection.runHandshake()
        await connection.runLoop();
        console.log("single", await singleValue(connection.status))
    }

    async function connect2() {

        //await new Promise((resolve) => ws.onopen = resolve);
        let wsCT = new WebsocketClientTransport({
            url: "ws://localhost:4342/_zwie/02/socket",
            wsCreator: (url) => new WebSocket(url)
        })
        let connector = new RSocketConnector({
            transport: wsCT
        })

        console.time("connect")
        let client = await connector.connect()
        console.timeEnd("connect")
        //@ts-ignore
        client.requestResponse({
            data: Buffer.from("hello"),
        })
        console.time("wait")
        await sleep(10_000)
        console.timeEnd("wait")

        console.log("closing...")
        client.close()
    }

    function dispose(connection: V1ClientConnection) {
        connection.close("goodbye");
        connections = connections.filter(c => c !== connection);

    }

    let url: string = "http://localhost:4342/";
    let textMode: boolean = true;
</script>

<h1>Welcome to Zwie</h1>
<h2>dev-0.0.10</h2>
<input bind:value={url}>
<input type="checkbox" bind:checked={textMode}>

<ContextMenuWrapper>
    <button class="btn btn-outline"
            on:click={abcde}>Connect
    </button>
    <button class="btn" on:click={connect2}>
        Connnect 2
    </button>

    <svelte:fragment slot="contextMenu">
        <a>Menu Here:</a>
        <button class="btn" on:click={()=>sendAlert({text:"Option 1"})}>Option 1</button>
        <button class="btn" on:click={()=>sendAlert({text:"Option 2"})}>Option 2</button>
        <button class="btn" on:click={()=>sendAlert({text:"Option 3"})}>Option 3</button>
        <button class="btn" on:click={()=>sendAlert({text:"Option 4"})}>Option 4</button>
        <button class="btn" on:click={()=>sendAlert({text:"Option 5"})}>Option 5</button>
    </svelte:fragment>
</ContextMenuWrapper>


<h3>Connections:</h3>
<ul>
    {#each connections as connection}
        <li>
            <ConnectionStatusBox connection={connection}/>
            <button on:click|preventDefault={()=>dispose(connection)}>X</button>
        </li>

    {/each}
</ul>
-->

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
