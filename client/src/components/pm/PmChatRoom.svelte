<script lang="ts">
    import {IGunChain} from "gun";
    import {gun} from "../../scripts/pm.js";


    function getMessages(): IGunChain<any, any, any> {
        return gun.get("zwie").get("pm").get("main").get("messages")
    }

    let username: string = Math.random().toString(36)

</script>
{#each getMessages().map().promise() as room}

    <div>
        <p>Username:</p>
        <input class="input input-bordered" bind:value={username}>
    </div>
    <div class="">

    </div>
{/each}


