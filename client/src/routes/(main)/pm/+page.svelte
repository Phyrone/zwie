<script lang="ts">

    import {MatrixClientW} from "../../../scripts/pm.js";


    /*
    gun.get("zwie").get("pm").get("main").get("messages").map().on((data, key) => {
        console.log(data, key);
    })

     */


    let client = new MatrixClientW("test", "https://matrix.org",)

    let usernameInput, passwordInput: string

    let userID: string | null = null;

    async function runLogin() {
        await client.login(usernameInput, passwordInput)
        await client.start()
        await client.saveLogin()
        userID = client.client.getUserId();
    }

    async function resumeLogin() {
        await client.resumeLogin()
        await client.start()
        await client.saveLogin()
        userID = client.client.getUserId();
    }


</script>
<input placeholder="Username" type="text" id="username" bind:value={usernameInput}>
<input placeholder="Password" type="password" id="password" bind:value={passwordInput}>
<button on:click={runLogin}>Login</button>
<button on:click={resumeLogin}>Resume</button>

<p>ID: {userID}</p>

<h2 class="text-lg">PM</h2>

<h1>Comming Soon</h1>