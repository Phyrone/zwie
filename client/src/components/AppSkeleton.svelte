<script lang="ts">
    import TopNavBar from "./TopNavBar.svelte";

</script>

<div class="flex flex-col h-full w-full">
    <div class="flex-none">
        <TopNavBar/>
    </div>
    <div class="flex-auto">
        <slot></slot>
    </div>

</div>
