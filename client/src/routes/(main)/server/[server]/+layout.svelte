<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(() => {

    })
    onDestroy(() => {

    })

</script>

<div class="bg-base-300 flex flex-row h-full bg-base-100">
    <div class="bg-base-300 flex flex-col overflow-y-auto bg-gray-800">
        <button class="btn ">Channel 1</button>
        <button class="btn ">Channel 2</button>
        <button class="btn ">Channel 3</button>
    </div>
    <div class="flex flex-auto h-full bg-gray-700">
        <slot/>
    </div>
</div>