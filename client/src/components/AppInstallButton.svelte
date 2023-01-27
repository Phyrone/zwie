<script lang="ts">
    import Icon from '@iconify/svelte';
    import installDesktop from '@iconify/icons-material-symbols/install-desktop';

    import {installPromt} from "../scripts/minor/update.js";


    async function install_clicked() {
        const {outcome} = await $installPromt!.prompt();
        if (outcome === 'accepted') {
            console.log('User accepted the A2HS prompt');
            installPromt.set(null)
        } else {
            console.log('User dismissed the A2HS prompt');
        }

    }
</script>

{#if $installPromt}
    <button class="btn btn-info text-lg" on:click|stopPropagation|preventDefault={install_clicked}>
        <Icon icon={installDesktop} inline={true} />
    </button>
{/if}

