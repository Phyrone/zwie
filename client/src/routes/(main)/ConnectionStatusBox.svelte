<script lang="ts">

    import type {Writable} from "svelte/store";
    import type {ConnectionStatus, V1ClientConnection} from "../../scripts/client-connection.js";

    export let connection: V1ClientConnection

    let statusWr: Writable<ConnectionStatus>
    $: statusWr = connection.status

    let status: ConnectionStatus
    $: status = $statusWr
</script>
<p>{status}</p>