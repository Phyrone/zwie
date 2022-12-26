<script lang="ts">
    import {fade} from "svelte/transition";
    import {createPopper} from "@popperjs/core";
    import type {Instance as PopperInstance} from "@popperjs/core";
    import {onDestroy, onMount} from "svelte";

    export let x: number;
    export let y: number;
    export let open: boolean;
    export let doubleContext: boolean = false;

    let popper: PopperInstance | undefined;
    let reference: HTMLElement;
    let contextMenuElement: HTMLElement;

    $:{
        if (popper) {
            popper.destroy()
            popper = undefined;
        }
        if (reference && contextMenuElement) {
            popper = createPopper(reference, contextMenuElement, {
                placement: "bottom-start",
                strategy: "absolute",
            })
        }
    }
    $:{
        if (popper && x && y) {
            popper.update().then()
        }
    }

    onMount(() => {

    })
    onDestroy(() => {
        if (popper) {
            popper.destroy()
            popper = undefined
        }
    })

    function close() {
        open = false;
    }

</script>

<span bind:this={reference} class:fixed={true} class:overflow-visible={true}
      style="top: {x}px; left: {y}px"></span>

<div class:fixed={true} class:top-0={true} class:left-0={true} class:h-screen={true} class:w-screen={true}>
    <div class:relative={true} class:h-screen={true} class:w-screen={true}>
        <div bind:this={contextMenuElement} on:contextmenu|preventDefault={()=>{}} transition:fade={{duration: 50,}}
             on:click={close}
             on:clickoutside={close}
             class="bg-base-300 shadow-2xl px-2 py-2 rounded-md flex flex-col">
            <slot></slot>
        </div>
    </div>

</div>

<svelte:body on:click|preventDefault={close} on:contextmenu|capture={(event)=>{
    if(!doubleContext){
        event.preventDefault();
        event.stopPropagation();
    }
    close()
}}></svelte:body>