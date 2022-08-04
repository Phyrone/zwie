/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./index.html",
        "./src/**/*.{vue,js,ts,jsx,tsx,svelte}",
    ],
    theme: {
        extend: {},
    },
    plugins: [require("daisyui"), require('tailwind-scrollbar'), require('@tailwindcss/forms'), require('@tailwindcss/line-clamp'), require('@tailwindcss/aspect-ratio')],
}
