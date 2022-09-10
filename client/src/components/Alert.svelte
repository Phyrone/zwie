<script lang="ts">
    import {fly} from 'svelte/transition';

    import type {Writable} from "svelte/store";
    import {DispatchedAlert} from "../scripts/messages.js";
    import type {AlertType} from "../scripts/messages.js";

    export let dispatchedAlert: DispatchedAlert

    let type: Writable<AlertType>
    $: type = dispatchedAlert.type

    let text: Writable<string>
    $: text = dispatchedAlert.text

    let closeable: Writable<boolean>
    $: closeable = dispatchedAlert.closeable

</script>
<div class="alert my-0.5 cursor-pointer"

     class:alert-info={$type === "info"}
     class:alert-error={$type === "error"}
     class:alert-warning={$type === "warning"}

     transition:fly
     on:click|preventDefault={()=>dispatchedAlert.click()}
>
    <i class="select-none fa-solid"
       class:fa-info={$type === "info"}
       class:fa-circle-exclamation={$type === "error"}
       class:fa-triangle-exclamation={$type === "warning"}

    ></i>
    <div class="select-none">
        <span>{$text}</span>
    </div>
    <div class="flex-none">
        {#if $closeable}
            <button class="hover:text-neutral-focus active:text-neutral"
                    on:click|preventDefault|stopPropagation={()=>dispatchedAlert.close()}><i
                    class="fa-solid fa-square-xmark"></i>
            </button>
        {/if}
    </div>
</div>