<script lang="ts">
    import {backend} from "../../scripts/backend";


    function connect() {
        backend.connect("http://localhost:4342/_zw_/proto01a").then((data) => {
            console.log("connection", data);
        });
    }
</script>

<button class="btn btn-success" on:click={connect}>Connect</button>