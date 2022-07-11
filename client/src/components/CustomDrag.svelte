<script lang="ts">

    import {window as appWindow} from "@tauri-apps/api";
    import {onDestroy, onMount} from "svelte";

    onMount(async () => {
        //await appWindow.getCurrent().setDecorations(false)
    })
    onDestroy(async () => {
        await appWindow.getCurrent().setDecorations(true)
    })
</script>
<div class="h-10 bg-green-500" data-tauri-drag-region>
</div>