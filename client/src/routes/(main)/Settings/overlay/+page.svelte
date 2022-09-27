<script lang="ts">
    import {onMount} from "svelte";
    import {themeChange} from "theme-change";
    import {themes} from "../../../../scripts/const.js";

    onMount(() => themeChange())
</script>
<div>
    <span>Theme</span>
    <select class="select" data-choose-theme>
        <option value="default">default</option>
        {#each themes as theme}
            <option value={theme}>{theme}</option>
        {/each}
    </select>

</div>