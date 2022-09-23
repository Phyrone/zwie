<script lang="ts">

    import {onDestroy} from "svelte";

    let int = 0;
</script>

<h1 on:click={()=>int++} class="select-none">Select here {int}</h1>