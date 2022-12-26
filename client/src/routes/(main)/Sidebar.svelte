<script lang="ts">
    import Icon from '@iconify/svelte';
    import accountIcon from '@iconify/icons-line-md/account';
    import homeMdTwotone from '@iconify/icons-line-md/home-md-twotone';
    import homeSimpleFilled from '@iconify/icons-line-md/home-simple-filled';
    import chatIcon from '@iconify/icons-material-symbols/chat.js';
</script>
<div class="flex-none flex flex-col">
    <a href="/dm/" class=" btn btn-primary btn-ghost"><Icon icon={chatIcon} /></a>
    <div class="divider my-0"></div>
</div>
<div class="flex-auto flex flex-col pt-1 overflow-x-auto scrollbar-none overscroll-auto">

    <a href="/server/s1/" class="btn btn-circle my-0.5">S1</a>
    <a href="/server/s2/" class="btn btn-circle my-0.5">S2</a>
    <a href="/server/s3/" class="btn btn-circle my-0.5">S3</a>
    <a href="/server/s4/" class="btn btn-circle my-0.5">S4</a>
    <a href="/server/s5/" class="btn btn-circle my-0.5">S5</a>
    <a href="/server/s6/" class="btn btn-circle my-0.5">S6</a>
    <a href="/server/s7/" class="btn btn-circle my-0.5">S7</a>
    <a href="/server/s8/" class="btn btn-circle my-0.5">S8</a>
    <a href="/server/s9/" class="btn btn-circle my-0.5">S9</a>
    <a href="/server/s10/" class="btn btn-circle my-0.5">S10</a>
    <a href="/server/s11/" class="btn btn-circle my-0.5">S11</a>
    <a href="/server/s12/" class="btn btn-circle my-0.5">S12</a>
    <a href="/server/s13/" class="btn btn-circle my-0.5">S13</a>
</div>
<!--
<div class="flex-none py-1 px-0.5">
    <div class="divider my-0"></div>
    <a href="/profile" class=" btn btn-primary btn-ghost"><Icon icon={accountIcon} inline={true}/></a>
</div>
-->