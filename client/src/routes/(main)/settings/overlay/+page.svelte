<script lang="ts">
    import {themes, theme} from "../../../../scripts/minor/theme-selector.js";
</script>
<div>
    <span>Theme</span>
    <select class="select" bind:value={$theme}>
        {#each themes as theme}
            <option value={theme}>{theme}</option>
        {/each}
    </select>
</div>