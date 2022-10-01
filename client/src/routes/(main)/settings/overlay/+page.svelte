<script lang="ts">
    import {onMount} from "svelte";
    import {themes, theme} from "../../../../scripts/theme-selector.js";
</script>
<div>
    <span>Theme</span>
    <select class="select" bind:value={$theme}>
        {#each themes as theme}
            <option value={theme}>{theme}</option>
        {/each}
    </select>

</div>