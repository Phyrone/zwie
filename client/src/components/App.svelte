<script lang="ts">

    import {onMount} from "svelte";
    import AppSkeleton from "./AppSkeleton.svelte";
    import * as SockJS from "sockjs-client/dist/sockjs";
    import VoiceConnectionStatusMenu from "./VoiceConnectionStatusMenu.svelte";

    let socket = new SockJS("http://localhost:4433/socket/");

    /*
    navigator.mediaDevices.enumerateDevices();
    let con = new RTCPeerConnection({
        iceServers: [
            {
                urls: [
                    "stun:stun1.l.google.com:19302",
                    "stun:stun2.l.google.com:19302",
                    "stun:stun3.l.google.com:19302",
                    "stun:stun4.l.google.com:19302",
                ],
            },
        ],
        iceCandidatePoolSize: 10,
    });
    onDestroy(() => con.close());
     */
    let displayElement: HTMLVideoElement;

</script>

<AppSkeleton>
    <div class="h-full w-full flex flex-wrap relative">

        <button class="btn" on:click={
        async ()=>displayElement.srcObject = await navigator.mediaDevices.getUserMedia({video: true})
        }>
            Webcam
        </button>
        <button class="btn" on:click={async ()=>{
try{
                    displayElement.srcObject=await navigator.mediaDevices.getDisplayMedia({video:true})
}catch(e){
            alert(e)
            }
        }}>
            Screen Share
        </button>
        <video bind:this={displayElement} autoplay></video>
        <VoiceConnectionStatusMenu></VoiceConnectionStatusMenu>
    </div>


</AppSkeleton>
