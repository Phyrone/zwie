<script lang="ts">
    import "../styles/main.sass";
    import WindowDragRegion from "$lib/WindowDragRegion.svelte";
    import {onMount} from "svelte";
    import AlertsContainer from "../components/AlertsContainer.svelte";
    import {init as init_updater} from "../scripts/update.js";
    import "../scripts/theme-selector.js";

    onMount(init_updater)
</script>
<!-- <WindowDragRegion/> -->
<slot/>
<AlertsContainer/>