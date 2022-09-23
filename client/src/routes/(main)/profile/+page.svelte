<script lang="ts">
    import Swal from 'sweetalert2';
    //import AvatarSelectPopUp from "../../../components/AvatarSelectPopUp.svelte"

    let imgID = 0;
    const Images = ["https://placeimg.com/192/192/people"]

    async function on_avatar_click() {

        //imgID=(imgID+1)%2;
        let AvatarSelectPopUp_promise = import("$lib/AvatarSelectPopUp.svelte");
        let alert = Swal.fire({
            title: "Select Avatar",
            html: "<span class='animate-bounce'>Waiting for Select</span>"
        });

        let AvatarSelectPopUp = (await AvatarSelectPopUp_promise).default;
        Swal.getHtmlContainer().innerHTML = "";
        let select_popup = new AvatarSelectPopUp({
            target: Swal.getHtmlContainer()
        })
        await alert;
        select_popup.$destroy();
    }
</script>


<span class="text-lg">Informations:</span>
 <span class="text-lg">Info about yourself:</span> 
<br>

<div class=" relative profile bg-base-300 rounded-md border border-gray-200 dark:border-gray-600 leading-6">
    <span class="text-lg text-white">Name:</span> 
    <span class="text-lg">Name example</span> 
    <button class="text-lg text-white rounded-md border border-gray-200 dark:border-gray-700">Change</button>
    <br>
    <br>
    <span class="text-lg text-white">E-Mail:</span>
    <span class="text-lg">example@mail.com</span>
    <button class="text-lg text-white rounded-md border border-gray-200 dark:border-gray-700">Change</button>
    <br>
    <br>
    <span class="text-lg text-white">ID: </span>
    <span class="text-lg">(comming soon)</span>
    <br>
    <div class="avatar absolute top-1 right-1 h-32 w-32 transition hover:scale-95">
        <div class="w-40 mask mask-squircle hover:shadow-xl" on:click={on_avatar_click}>
            <img alt={Images[imgID]} src={Images[imgID]}/>
        </div>
    </div>
</div>