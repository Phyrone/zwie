<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(() => { 

    })
    onDestroy(() => {

    })

</script>

<div class="flex flex">

    <button class="btn btn-xs">Let´s talk a bit</button>

    <div class="bg-base-300 flex flex-col bg-gray-400">
        <div class="bg-base-300 flex flex-col overflow-y-auto bg-gray-800">
                <button class="btn btn-xs">Channel 1</button>   
                <button class="btn btn-xs">Channel 2</button>  
                <button class="btn btn-xs">Channel 3</button>             
        </div>
    </div>
    <div class="flex-auto">
        <slot/>
        </div>
</div>