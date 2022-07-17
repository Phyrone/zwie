<script lang="ts">
    //import type {ConnectionStatus} from "../scripts/types.js";

    import Swal from "sweetalert2";

    export let muted: boolean = false;
    export let deafed: boolean = false;

    async function toggleMute() {
        muted = !muted;
    }
    async function toggleDeaf() {
        deafed = !deafed;
    }

</script>
<ul class="menu menu-horizontal bg-base-100 rounded-box absolute shadow bottom-0">
    <li>
        <button on:click|preventDefault={toggleDeaf}>
            <label class="swap select-none">
                <input type="checkbox" bind:checked={deafed}>
                <i class="swap-on fa-solid fa-volume-xmark"></i>
                <i class="swap-off fa-solid fa-volume-high"></i>
            </label>
        </button>

    </li>
    <li>
        <button on:click|preventDefault={toggleMute}>
            <label class="swap select-none">
                <input type="checkbox" bind:checked={muted}>
                <i class="swap-on fa-solid fa-microphone-slash"></i>
                <i class="swap-off fa-solid fa-microphone"></i>
            </label>
        </button>
    </li>
    <li>
        <button><i class="fa-solid fa-circle-info"></i></button>
    </li>
</ul>