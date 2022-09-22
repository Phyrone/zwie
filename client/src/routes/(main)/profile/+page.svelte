<script lang="ts">
import Swal from "sweetalert2" 
import AvatarSelectPopUp from "../../../components/AvatarSelectPopUp.svelte"

let imgID=0;
const Images=["https://placeimg.com/192/192/people"]

function on_avatar_click(){
    
    //imgID=(imgID+1)%2;

    Swal.fire({
        

    })

    new AvatarSelectPopUp({
        target: Swal.getHtmlContainer()

    })
}
</script>


 <span class="text-lg">Informations:</span> 
<br>

<div class="profile bg-base-300 rounded-md border border-gray-200 dark:border-gray-600 leading-6">
    <span class="text-lg text-white">Name:</span> 
    <span class="text-lg">Name example</span>
    <br>
    <br>
    <span class="text-lg text-white">Your E-Mail:</span>
    <span class="text-lg">example@mail.com</span>
    <br>
    <br>
    <span class="text-lg text-white">Your ID: </span>
    <span class="text-lg">(comming soon)</span>
    <br>
     <div class="avatar absolute top-24 right-1 h-32 w-32 dark:hover:scale-95">
         <div class="w-40 mask mask-squircle" on:click={on_avatar_click}>
              <img src={Images[imgID]}/>
         </div>
     </div>
</div>