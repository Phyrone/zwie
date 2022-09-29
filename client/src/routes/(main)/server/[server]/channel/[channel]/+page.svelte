<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(()=>{
        console.log("loading channel...")
    })
    onDestroy(()=>{
        console.log("closing channel...")
    })

</script>



    <div class="h-auto w-full">
        <card id="chat" name="chat">
            User 1: Hallo
            sassssssssssssssssssssssssssssssssssss
            <br>
            sssss
            sssss
        </card>
    </div>
    <!--TODO: Die Inputbar muss auf den bottom-0 Wert; Ich finde den Fehler nicht-->
    <div class="relative bottom-0">
        <input type="text" placeholder="Type here" class="input w-full bg-gray-300 input-md bottom-0"/>
    </div>
