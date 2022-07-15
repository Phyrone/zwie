<script lang="ts">
    import {createAvatar} from '@dicebear/avatars';
    import * as style from '@dicebear/avatars-bottts-sprites';


    export let seed: string = "";

    let svg;

    $://@ts-ignore
        svg = createAvatar(style, {
            seed: seed,
            dataUri: true,


        })
</script>
{#if svg}
    <img src={svg} alt={seed} class="w-full h-full">
{:else}
    <div class="w-full h-full bg-gray-200"></div>
{/if}