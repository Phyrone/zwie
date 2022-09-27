<script lang="ts">

    import {onDestroy, onMount,} from "svelte";

    onMount(() => {

    })
    onDestroy(() => {

    })

</script>

<<<<<<< Updated upstream:client/src/routes/(main)/server/[server]/+layout.svelte
<div class="flex flex-row h-full bg-base-300">
    <div class=" flex flex-col overflow-y-auto bg-base-200/75">
        <button class="btn ">Channel 1</button>
        <button class="btn ">Channel 2</button>
        <button class="btn ">Channel 3</button>
    </div>
    <div class="flex flex-auto h-full bg-base-200">
        <slot/>

<div class="flex flex-row h-full">

    <div class="flex flex-col static bg-zinc-400 h-full">
    <ul class="h-full w-auto">
        <details> 
            <summary class="bg-blue-400  text-gray-900 border border-solid border-gray-400">Channel 1</summary>
             <ul>
             <li class="text-right"><button class="btn btn-xs">User 1</button> </li>
             <li class="text-right"><button class="btn btn-xs">User 2</button> </li>
             </ul>
        </details>
    </ul>
    </div>

    <div class="flex flex-col w-11/12 bg-zinc-600">
                <card class="w-full z-0 align-text-bottom" id="chat" name="chat">
                    User 1: Hallo
                </card>

        <div class="relative h-full w-full">
            <div class="absolute inset-x-0 bottom-0 h-12 "><input type="text" placeholder="Type here" class="input w-full bg-gray-300" /></div>
          </div>
    </div>


    <div class="flex flex-col h-full">
        <div class="flex flex-auto h-full bg-gray-500 z-0">
            <div class=" h-32 w-full">
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
>>>>>>> Stashed changes:client/src/routes/(main)/s/[server]/+layout.svelte
    </div>
</div>