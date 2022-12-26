<script lang="ts">
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
    <button class="btn btn-info" on:click|stopPropagation|preventDefault={install_clicked}><i
            class="fa-solid fa-download"></i></button>
{/if}

