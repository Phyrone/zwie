import{w as r}from"./index-f2759465.js";var t=(a=>(a.default="default",a.light="light",a.dark="dark",a.cupcake="cupcake",a.bumblebee="bumblebee",a.emerald="emerald",a.corporate="corporate",a.synthwave="synthwave",a.retro="retro",a.cyberpunk="cyberpunk",a.valentine="valentine",a.halloween="halloween",a.garden="garden",a.forest="forest",a.aqua="aqua",a.lofi="lofi",a.pastel="pastel",a.fantasy="fantasy",a.wireframe="wireframe",a.black="black",a.luxury="luxury",a.dracula="dracula",a.cmyk="cmyk",a.autumn="autumn",a.business="business",a.acid="acid",a.lemonade="lemonade",a.night="night",a.coffee="coffee",a.winter="winter",a))(t||{});const c=Object.values(t),l=r(localStorage.getItem("theme")||"default"),n="theme";l.subscribe(a=>{console.log("theme changed",a),document.documentElement.setAttribute("data-theme",a),localStorage.setItem(n,a)});export{l as a,c as t};