<script lang="ts">
    let promt;
    window.addEventListener('beforeinstallprompt', (event) => {
        event.preventDefault();
        promt = event;
    })

    function install_clicked() {
        promt.prompt();
    }
</script>

{#if promt}
    <button class="btn btn-info" on:click|stopPropagation|preventDefault={install_clicked}>Install</button>
{/if}

