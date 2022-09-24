<table>
    <tbody>
        <tr>
              <div class="flex flex-row relative h-32 w-32 border-solid">
                     <td>
                         <div class="flex flex-col absolute border border-solid border-gray-500">

                         <span class="text-white-300 decoration-4">App-Settings</span>
                         <a href="/Settings/Overlay" class="btn btn-ghost border border-solid border-gray-500">Overlay</a>
                         <a href="/Settings/Video" class="btn btn-ghost border border-solid border-gray-500">Video</a>
                         <a href="/Settings/Audio" class="btn btn-ghost border border-solid border-gray-500">Audio</a>
                         <a href="/Settings/Language" class="btn btn-ghost border border-solid border-gray-500">Language</a>



                        </div>
                    </td>
                    
              </div>
              <td>
                <div class="flex flex-auto w-full h-full">
                    <slot/>
                </div>
    
            </td>
    </tr>
    </tbody>
</table>