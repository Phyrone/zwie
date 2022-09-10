<div class="h-screen w-screen flex flex-row">
    <div class="bg-base-300 flex flex-col flex-none align-middle justify-center px-1.5">
        <div class="flex-auto flex flex-col pt-1 overflow-x-auto scrollbar-thin">
            <a href="/s/s1/" class="btn btn-circle my-0.5">S1</a>
            <a href="/s/s2/" class="btn btn-circle my-0.5">S2</a>
            <a href="/s/s3/" class="btn btn-circle my-0.5">S3</a>
            <a href="/s/s4/" class="btn btn-circle my-0.5">S4</a>
            <a href="/s/s5/" class="btn btn-circle my-0.5">S5</a>
            <a href="/s/s6/" class="btn btn-circle my-0.5">S6</a>
            <a href="/s/s7/" class="btn btn-circle my-0.5">S7</a>
            <a href="/s/s8/" class="btn btn-circle my-0.5">S8</a>
            <a href="/s/s9/" class="btn btn-circle my-0.5">S9</a>
            <a href="/s/s10/" class="btn btn-circle my-0.5">S10</a>
            <a href="/s/s11/" class="btn btn-circle my-0.5">S11</a>
            <a href="/s/s12/" class="btn btn-circle my-0.5">S12</a>
            <a href="/s/s13/" class="btn btn-circle my-0.5">S13</a>
        </div>
        <div class="flex-none py-1 px-0.5">
            <div class="divider my-0"></div>
            <button class=" btn btn-primary btn-ghost"><i class="fa-solid fa-user"></i></button>
        </div>
    </div>
    <div class="flex-auto">
        <nav class="navbar">
            <a href="/" sveltekit:prefetch class="btn btn-ghost">Home</a>
        </nav>
        <slot/>
    </div>

</div>
