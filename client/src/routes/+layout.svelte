<script lang="ts">
    import "../styles/main.sass"
    import {onMount} from "svelte";
    import AlertsContainer from "../components/AlertsContainer.svelte";
    import {init as init_updater} from "../scripts/update.js";

    onMount(init_updater)
</script>
<slot/>
<AlertsContainer/>