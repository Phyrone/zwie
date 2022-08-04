<script lang="ts">
    const size = 200;
    let items: string[] = [];
    for (let i = 0; i < size; i++) {
        items.push(`S${i}`);
    }
    import {createAvatar} from '@dicebear/avatars';
    import * as avata_style from '@dicebear/avatars-identicon-sprites';
    import * as initias_style from '@dicebear/avatars-initials-sprites';

    //@ts-ignore
    let avatar = createAvatar(avata_style, {
        seed: 'custom-seed',
        dataUri: true,
    })

</script>
<div class="bg-base-300 w-16 flex flex-col px-2 pb-1 pt-2 flex-none">
    <div class="flex flex-col flex-auto overflow-y-auto w-full scrollbar-none overscroll-none">
        {#each items as item}
            <img src={createAvatar(initias_style,{
                seed: item,
                dataUri: true,
            })} alt={item} width="100%" height="100%" class="
            btn bg-transparent border-none p-0 w-full mb-1 mask mask-circle
            hover:mask-squircle
            ">

        {/each}
    </div>
    <div class="divider my-1"></div>
    <button class="btn btn-ghost rounded-lg w-full p-1 mb-1"><img width="100%" height="100%" src={avatar} alt=""></button>
</div>
