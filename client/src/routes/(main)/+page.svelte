<script lang="ts">
    import {backend} from "../../scripts/backend";


    function connect() {
        backend.connect("http://0.0.0.0:4342/").then((data) => {
            console.log("connection", data);
        });
    }
</script>

<button class="btn btn-success" on:click={connect}>Connect</button>