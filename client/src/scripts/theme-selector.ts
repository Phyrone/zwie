import {writable} from "svelte/store";
import * as localforage from "localforage";

export enum Theme {
    default = "default",
    light = "light",
    dark = "dark",
    cupcake = "cupcake",
    bumblebee = "bumblebee",
    emerald = "emerald",
    corporate = "corporate",
    synthwave = "synthwave",
    retro = "retro",
    cyberpunk = "cyberpunk",
    valentine = "valentine",
    halloween = "halloween",
    garden = "garden",
    forest = "forest",
    aqua = "aqua",
    lofi = "lofi",
    pastel = "pastel",
    fantasy = "fantasy",
    wireframe = "wireframe",
    black = "black",
    luxury = "luxury",
    dracula = "dracula",
    cmyk = "cmyk",
    autumn = "autumn",
    business = "business",
    acid = "acid",
    lemonade = "lemonade",
    night = "night",
    coffee = "coffee",
    winter = "winter"
}

export const themes = Object.values(Theme);

export const theme = writable<string>(/*localStorage.getItem('theme') ||*/ Theme.default)

const localstore_key = "zwie::theme"

let dbLoaded = false;
theme.subscribe((theme) => {
    console.log("theme changed", theme)
    document.documentElement.setAttribute('data-theme', theme)
    //localStorage.setItem(localstore_key, theme)
    if (dbLoaded)
        localforage.setItem(localstore_key, theme).then()
});

localforage.ready().then(() =>
    localforage.getItem<string>(localstore_key).then((themeData) => {
        dbLoaded = true
        if (themeData) {
            theme.set(themeData)
        }
    })
)

