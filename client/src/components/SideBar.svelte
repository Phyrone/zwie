<script lang="ts">
    const size = 200;
    let items: string[] = [];
    for (let i = 0; i < size; i++) {
        items.push(`S${i}`);
    }

</script>
<div class="bg-base-300 w-20 flex flex-col px-2 py-1">

    <div class="flex flex-col flex-auto overflow-y-auto w-full scrollbar-thin scrollbar-thumb-gray-300 dark:scrollbar-thumb-gray-900">
        {#each items as item}
            <button class="btn btn-ghost w-full">{item}</button>
        {/each}

    </div>

    <div class="divider my-1"></div>
    <button class="btn btn-ghost rounded-lg w-full">P</button>
</div>