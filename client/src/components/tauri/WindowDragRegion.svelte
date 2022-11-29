<script lang="ts">
    import {getCurrent} from "@tauri-apps/api/window";
    import {onDestroy, onMount} from "svelte";

    let window = getCurrent();

    let maximized = false
    window.onScaleChanged(async (a) => {
        maximized = await window.isMaximized()
    })
    onMount(() => window.setDecorations(false))
    onDestroy(() => window.setDecorations(true))
</script>

<div data-tauri-drag-region class="flex px-2 py-1">
    <div class="flex-auto select-none"></div>
    <div class="btn" on:click={()=>window.close()}><i class="fa-solid fa-rectangle-xmark"></i></div>
    <div class="btn" on:click={()=>window.hide()}><i class="fa-solid fa-window-minimize"></i></div>
    <div class="btn" on:click={()=>{
        window.maximize()

    }}>
        <i class="fa-solid" class:fa-maximize={!maximized} class:fa-minimize={maximized}></i>
    </div>
</div>