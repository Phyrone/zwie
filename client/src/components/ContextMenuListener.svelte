<script lang="ts">


    import ContextMenuWrapper from "$lib/ContextMenuWrapper.svelte";

    let contextMenuOpen = false;

    let contextMenuX = 0;
    let contextMenuY = 0;

    async function openContextMenu(event) {
        contextMenuOpen = true;
        contextMenuX = event.clientY;
        contextMenuY = event.clientX;
    }

    export let doubleContext = true;

</script>

<span on:contextmenu|preventDefault={openContextMenu}>
    <slot></slot>
</span>

{#if contextMenuOpen}
    <ContextMenuWrapper bind:open={contextMenuOpen} x={contextMenuX} y={contextMenuY} doubleContext={doubleContext}>
        <slot name="contextMenu"></slot>
    </ContextMenuWrapper>
{/if}