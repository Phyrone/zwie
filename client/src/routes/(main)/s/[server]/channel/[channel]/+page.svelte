<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(()=>{
        console.log("loading channel...")
    })
    onDestroy(()=>{
        console.log("closing channel...")
    })

</script>

<h1>Channel soon here</h1>