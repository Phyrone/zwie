<script lang="ts">
    import {alerts} from "../../scripts/minor/alerts.js";
    import Alert from "./Alert.svelte";

</script>

<div class="absolute top-0 right-0 h-full max-w-full flex flex-col overflow-x-auto z-40">

    {#each $alerts as alert}
        <Alert dispatchedAlert={alert}/>
    {/each}
</div>