<script>

const allRanges = document.querySelectorAll(".range-wrap");
allRanges.forEach(wrap => {
  const range = wrap.querySelector(".range");
  const bubble = wrap.querySelector(".bubble");

  range.addEventListener("input", () => {
    setBubble(range, bubble);
  });
  setBubble(range, bubble);
});

function setBubble(range, bubble) {
  const val = range.value;
  const min = range.min ? range.min : 0;
  const max = range.max ? range.max : 100;
  const newVal = Number(((val - min) * 100) / (max - min));
  bubble.innerHTML = val;
  bubble.style.left = `calc(${newVal}% + (${8 - newVal * 0.15}px))`;
}


</script>

<style>
.range-wrap {
    position: relative;
    margin: 0 auto 3rem;
    width: 100%;
    height: 100%;
    
  }
  .range {
    width: 100%;
  }
  .bubble {
    background: gray;
    color: white;
    padding: 4px 12px;
    position: absolute;
    border-radius: 4px;
    left: 50%;
    transform: translateX(-50%);
  }
  .bubble::after {
    content: "";
    position: absolute;
    width: 2px;
    height: 4px;
    background: gray;
    top: -1px;
    left: 50%;
  }
</style>



<div>
    <div class="flex">
    <table class="table-auto place-content-center">
        <tbody>
                <tr> 
                    <td>
                        <div class="mr-80">
                            <b><span>Microphone input</span></b>
                        </div>
                    </td>

                    <td>
                        <div class="mr-80">
                            <b><span>Audio output</span></b>
                        </div>
                    </td>
                    
                    <td>
                        <div class="mr-80">
                            <b><span>Input devices</span></b>
                        </div>
                    </td>

                    <td>
                        <div class="mr-80">
                            <b><span>Output devices</span></b>
                        </div>
                    </td>
                </tr>
                    <tr>  
                        <div class="range-wrap flex ml-80">
                            <input type="range" class="range">
                            <output class="bubble"></output>
                        </div>
                    </tr>                     
        </tbody>
    </table>
    </div>
</div>