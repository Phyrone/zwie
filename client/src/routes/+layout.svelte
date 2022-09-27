<script lang="ts">
    import "../styles/main.sass"
    import {onMount} from "svelte";
    import AlertsContainer from "../components/AlertsContainer.svelte";
    import {init as init_updater} from "../scripts/update.js";
    import {themeChange} from "theme-change"

    onMount(init_updater)
    onMount(() => themeChange(false))
</script>
<slot/>
<AlertsContainer/>