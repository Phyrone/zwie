<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(() => {

    })
    onDestroy(() => {

    })

</script>

<div class="flex flex-row h-full fixed w-full">

    <div class="flex flex-col static bg-gray-500 h-full w-2/12">
        <ul class="h-full w-auto">
            <details> 
                <summary class="bg-blue-400  text-gray-900 border border-solid border-gray-400">Channel 1</summary>
                <ul>
                <li class="text-right"><button class="btn btn-xs">User 1</button> </li>
                <li class="text-right"><button class="btn btn-xs">User 2</button> </li>
                </ul>
            </details>
        </ul>

        <div class="static h-auto w-full bg-gray-500 bottom-0 left-0 border border-solid border-gray-700">

            <div class="relative bg-gray-500 h-auto w-full border border-solid border-gray-700 border-">
            <!--Platz für images der User--> 
            <span>Voicechat</span>

            </div>
        

            <div class="relative h-32 bg-gray-500 bottom-0 left-0">
                <div class="absolute h-auto w-auto">
                    <button class="btn btn-circle bg-green-600">CALL</button>
                    <button class="btn btn-circle bg-red-600 w-auto h-auto">DISCONNECT</button>
                    <button class="btn btn-circle bg-gray-500">MUTE</button>
                </div>
            </div>
        </div>
    </div>


    <div class="flex flex-col relative w-8/12 bg-zinc-600 h-full">
        <div class="h-auto w-full">
            <card id="chat" name="chat">
                User 1: Hallo
                sassssssssssssssssssssssssssssssssssss
                <br>
                sssss
                sssss
            </card>
        </div>
        <!--TODO: Die Inputbar muss auf den bottom-0; Ich finde den Fehler nicht-->
        <div class="relative bottom-0">
            <input type="text" placeholder="Type here" class="input w-full bg-gray-300 input-md bottom-0"/>
        </div>
    </div>


    <div class="flex flex-col h-full w-2/12">
        <div class="flex flex-auto h-full bg-gray-500 z-0">
            <div class=" h-32 w-auto">
                <div class=" top-0 h-auto w-full z-10 bg-zinc-700">
                    <table>
                        <tbody>
                               <td><button class="btn btn-ghost btn-xs border border-solid border-gray-400">Widgets</button></td>
                               <td><button class="btn btn-ghost btn-xs border border-solid border-gray-400">Users</button></td>
                               <td><button class="btn btn-ghost btn-xs border border-solid border-gray-400">(not filled)</button></td>
                        </table>
                </div>
            </div>
        </div>
    </div>
</div>