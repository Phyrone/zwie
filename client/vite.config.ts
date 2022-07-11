import {svelte} from '@sveltejs/vite-plugin-svelte'
import {defineConfig} from 'vite'
import {imagetools} from 'vite-imagetools'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [svelte(), imagetools()]
})
