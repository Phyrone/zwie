<script lang="ts">

    import Icon from "@iconify/svelte";
    import micIcon from '@iconify/icons-material-symbols/mic';
    import headphonesIcon from '@iconify/icons-material-symbols/headphones';


    import {onDestroy, onMount,} from "svelte";

    onMount(() => {

    })
    onDestroy(() => {

    })

</script>

<!--
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
        
        <div class="relative h-40 w-full bg-gray-500 bottom-0 left-0 border border-solid border-gray-700">

            <div class="relative bg-gray-500 h-auto w-full border border-solid border-gray-700 border-">
            -->
<!--Platz für images der User-->
<!--
<span>Voicechat</span>

</div>


<div class="flex flex-row h-auto bg-gray-500 bottom-0 left-0 ">
    <button class="btn btn-circle bg-green-600">CALL</button>
    <button class="btn btn-circle bg-red-600 w-auto">DISCONNECT</button>
    <button class="btn btn-circle bg-gray-500">MUTE</button>
</div>
</div>
</div>

<div class="flex flex-col relative w-8/12 bg-zinc-600 h-full">
<div class="h-auto w-full top-0">
<span>
    User 1: Hallo
    sassssssssssssssssssssssssssssssssssss
    <br>
    sssss
    sssss
</span>
</div>

<div class="fixed w-8/12 bottom-0 h-20 bg-gray-800">
    <input type="text" placeholder="Type here" class="absolute input w-full bg-gray-300 bottom-3.5 text-gray-800"/>
</div>
</div>


<div class="flex flex-col h-full w-2/12 -right-0">
<div class="flex flex-auto bg-gray-500 w-full">
<div class="h-auto w-full">
    <div class="top-0 h-auto w-full bg-zinc-700">
        <table class="w-full">
            <tbody>
                   <td class="w-auto"><button class="btn btn-ghost btn-xs border border-solid border-gray-400 w-full">Widgets</button></td>
                   <td class="w-auto"><button class="btn btn-ghost btn-xs border border-solid border-gray-400 w-full">Users</button></td>
                   <td class="w-auto"><button class="btn btn-ghost btn-xs border border-solid border-gray-400 w-full">(not filled)</button></td>
            </table>
    </div>
</div>
</div>
</div>
</div>
-->
<div class="h-full flex flex-row">

    <div class="bg-green-600 flex flex-col rounded-md rounded-md my-1 mx-2">
        <div class="flex-auto">

        </div>
        <div class="flex-none">

            <button class="btn ">
                <Icon icon={micIcon}/>
            </button>
            <button class="btn ">
                <Icon icon={headphonesIcon}/>
            </button>
        </div>
    </div>
    <div class="flex-auto bg-red-600 flex flex-col rounded-md">
        <div class="overflow-auto flex-auto">

        </div>
<div class="flex-none">
    <form class="form-control w-full" on:submit|preventDefault={()=>alert("send")}>
        <label class="input-group input-lg w-full">
            <input required class="input input-lg w-full">
            <button class="btn">Send</button>
        </label>
    </form>


</div>
    </div>
</div>