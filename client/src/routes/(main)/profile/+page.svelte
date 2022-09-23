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

    async function on_E_Mail_click(){
        let EMail_Change = import("$lib/E-Mail_Change.svelte");

    }

    async function on_Name_click(){
        let NameChange = import("$lib/NameChange.svelte");

    }

</script>
<!--TODO: -->

<span class="text-lg">Informations:</span>
<br>

<div class=" relative profile bg-base-300 rounded-md border border-gray-200 dark:border-gray-600 leading-6">
    <table class="table-auto">
        <tbody>
            <tr>
                <td><span class="text-lg text-white">Name:</span></td>
                <td><span class="text-lg">Name example</span></td>
                <td><button class="btn btn-xs" on:click={on_Name_click}>Change</button></td>
            </tr>
            <br>
            <tr>
                <td><span class="text-lg text-white">E-Mail:</span></td>
                <td><span class="text-lg">example@mail.com</span></td>
                <td><button class="btn btn-xs" on:click={on_E_Mail_click}>Change</button></td>
            </tr>
            <br>
            <tr>
                <td><span class="text-lg text-white">Code:</span></td>
                <td><span class="text-lg">(comming soon)</span></td>
            </tr>
        </tbody>
    </table>
    <div class="avatar absolute top-1 right-1 h-32 w-32 transition hover:scale-95">
        <div class="w-40 mask mask-squircle hover:shadow-xl" on:click={on_avatar_click}>
            <img alt={Images[imgID]} src={Images[imgID]}/>
        </div>
    </div>
</div>