
import {sveltekit} from '@sveltejs/kit/vite';
import type {UserConfig} from 'vite';
//import {imagetools} from 'vite-imagetools'

const config: UserConfig = {
    plugins: [sveltekit(), /* imagetools(), */],
    clearScreen: false,
    envPrefix: ['VITE_', 'TAURI_'],
    server: {
        port: 5173,
        strictPort: true,
    },
    build:{
        sourcemap: "inline",
        minify: "esbuild",
    }
};

// noinspection JSUnusedGlobalSymbols
export default config;
