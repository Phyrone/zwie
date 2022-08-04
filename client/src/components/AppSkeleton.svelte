<script lang="ts">
    import SideBar from "./SideBar.svelte";
    import TopNavBar from "./TopNavBar.svelte";

</script>

<div class="flex flex-row h-full w-full">
    <SideBar/>
    <div class="flex flex-auto flex-col">
        <div class="flex-none">
            <TopNavBar/>
        </div>
        <div class="flex-auto">
            <slot></slot>
        </div>
    </div>


</div>
