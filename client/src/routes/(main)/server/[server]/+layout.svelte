<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(() => {

    })
    onDestroy(() => {

    })

</script>

<div class="flex flex-row h-full bg-base-300">
    <div class=" flex flex-col overflow-y-auto bg-base-200/75">
        <button class="btn ">Channel 1</button>
        <button class="btn ">Channel 2</button>
        <button class="btn ">Channel 3</button>
    </div>
    <div class="flex flex-auto h-full bg-base-200">
        <slot/>
    </div>
</div>