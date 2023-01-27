<script lang="ts">
    import "../styles/main.sass";
    import "../scripts/minor/compat.js"
    import {onMount} from "svelte";
    import AlertsContainer from "../components/alert/AlertsContainer.svelte";
    import {init as init_updater} from "../scripts/minor/update.js";
    import "../scripts/minor/theme-selector.js";
    import * as localforage from "localforage";

    onMount(async ()=>{
        await localforage.ready()
        console.log("database driver:", localforage.driver())
    })
    onMount(init_updater)
</script>
<!-- <WindowDragRegion/> -->
<slot/>
<AlertsContainer/>