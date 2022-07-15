<script lang="ts">
    import {onMount} from "svelte";
    import AppSkeleton from "./AppSkeleton.svelte";
    import platform from "platform";

    let webcam: HTMLVideoElement;

    async function main() {
        webcam.srcObject = await navigator.mediaDevices.getUserMedia({
            video: true,
            audio: false,
        });

        console.log(platform)

    }

    let displayVideoPlayer: HTMLVideoElement;

    async function screenCastClicked() {
        try {
            displayVideoPlayer.srcObject = await navigator.mediaDevices.getDisplayMedia({
                video: true,
                audio: false,
            })
        }catch (e) {
            console.error(e)
            alert(JSON.stringify(e))
        }


    }

    onMount(main)
</script>

<AppSkeleton>
    <div class="h-full w-full flex flex-wrap">
        <button data-dismiss="modal" on:click={screenCastClicked}>Screen</button>
        <p>{navigator.mediaDevices}</p>
        <video class="w-1/3" bind:this={webcam} autoplay></video>

        <video class="w-1/3" bind:this={displayVideoPlayer} autoplay></video>
    </div>


</AppSkeleton>