<script>
    import TopNavBar from "./TopNavBar.svelte";
    import BotBar from "./BotBar.svelte";
</script>

<div class="flex flex-col h-full w-full">
    <div class="flex-none">
        <TopNavBar/>
    </div>
    <div class="flex-auto shadow-md">
        <slot></slot>
    </div>
    <div class="flex-none">
        <BotBar/>
    </div>

</div>
