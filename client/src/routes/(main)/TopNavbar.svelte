<script lang="ts">
    import AppInstallButton from "$lib/AppInstallButton.svelte";
    import accountIcon from "@iconify/icons-line-md/account";
    import homeMdTwotone from "@iconify/icons-line-md/home-md-twotone";
    import Icon from "@iconify/svelte";


</script>


<nav class="navbar bg-base-200 shadow-sm sticky top-0 py-0">
    <div class="navbar-start">
        <a href="/" class="btn btn-ghost"><Icon icon={homeMdTwotone} inline={true}/></a>
        <a href="/settings/" class="btn btn-ghost">Settings</a>
        <a href="/toolbox/" class="btn btn-ghost">Toolbox</a>
        <a href="/plugins/" class="btn btn-ghost">Plugins</a>
    </div>
    <div class="navbar-end">
        <AppInstallButton/>
        <a href="/profile" class=" btn btn-primary btn-ghost"><Icon icon={accountIcon} inline={true}/></a>
    </div>


</nav>