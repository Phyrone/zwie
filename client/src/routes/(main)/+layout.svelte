<script>
    import AppInstallButton from "../../components/AppInstallButton.svelte";
    import Sidebar from "./Sidebar.svelte";
    import TopNavbar from "./TopNavbar.svelte";
</script>

<div class="h-full w-full flex flex-row">
    <div class="bg-base-300 flex flex-col flex-none align-middle justify-center px-1.5">
        <Sidebar/>

    </div>
    <div class="min-h-screen flex-auto overflow-auto flex flex-col">
        <TopNavbar/>

        <slot/>
    </div>

</div>
