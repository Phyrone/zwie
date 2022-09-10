<script lang="ts">


    import Swal from "sweetalert2";
    import {sendAlert} from "../../scripts/messages.js";

    let counter = 1;
</script>

<h1>Welcome to Zwie</h1>
<h2>dev-0.0.10</h2>
<button class="btn" on:click={()=>sendAlert({
    text: "Normal Alert "+counter++,
    onClick: ()=>Swal.fire({
        text: "Click",
    })
})}>Normal Alert
</button>
<button class="btn btn-info" on:click={()=>sendAlert({
    text: "Info Alert "+counter++,
    type: "info",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "info"
    })
})}>Info Alert
</button>

<button class="btn btn-warning" on:click={()=>sendAlert({
    text: "Warning Alert "+counter++,
    type: "warning",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "warning"
    })
})}>Warning Alert
</button>

<button class="btn btn-error" on:click={()=>sendAlert({
    text: "Error Alert "+counter++,
    type: "error",
    onClick: ()=>Swal.fire({
        text: "Click",
        icon: "error"
    })
})}>Error Alert
</button>