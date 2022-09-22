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




<div class="profile bg-black">
    <span class="text-lg text-white">Name:</span> 
    <span class="text-lg">ExampleName</span>   <input type="text" placeholder="Click to change" class="input input-bordered input-xs max-w-xs" />
    <br>
    <br>
    <span class="text-lg text-white">Your E-Mail:</span>
    <span class="text-lg">example@mail.com</span>
    <br>
    <br>
    <span class="text-lg text-white">Your ID: (comming soon)</span>
    <br>
    <br>
    <div class="avatar">
        <div class="w-24 mask mask-squircle" on:click={on_avatar_click}>
            <img src={Images[imgID]} />
        </div>
     </div>
    </div>