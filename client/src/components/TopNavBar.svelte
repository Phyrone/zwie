<script lang="ts">
    import DiceRoboImage from "./DiceRoboImage.svelte";
    let name:string = "User";
</script>

<div class="navbar shadow">
    <div class="navbar-start">
        <button class="btn btn-ghost">Home</button>
    </div>
    <div class="navbar-end">
        <div class="flex-auto"></div>
        <input class="input" type="text" placeholder="Name" bind:value={name}>
        <button class="btn flex-none w-20">
            <DiceRoboImage seed={name}></DiceRoboImage>
        </button>
    </div>
</div>
