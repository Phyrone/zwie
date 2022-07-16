<script lang="ts">

    import AppSkeleton from "./AppSkeleton.svelte";

    //let socket = new SockJS("http://localhost:4433/socket/");

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


</script>

<AppSkeleton>
    <div class="h-full w-full flex flex-wrap">

    </div>
</AppSkeleton>
