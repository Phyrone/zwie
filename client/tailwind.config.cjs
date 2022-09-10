/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{js,ts,jsx,tsx,svelte,html,css,sass,scss}",
    ],
    theme: {
        extend: {},
    },
    plugins: [require("daisyui"), require('tailwind-scrollbar'), require('@tailwindcss/forms'), require('@tailwindcss/line-clamp'), require('@tailwindcss/aspect-ratio')],
}
