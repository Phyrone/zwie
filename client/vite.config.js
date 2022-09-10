import { sveltekit } from '@sveltejs/kit/vite';
import { imagetools } from 'vite-imagetools';
const config = {
    plugins: [sveltekit(), imagetools(),],
    clearScreen: false,
    envPrefix: ['VITE_', 'TAURI_'],
    server: {
        port: 5173,
        strictPort: true,
    },
};
// noinspection JSUnusedGlobalSymbols
export default config;
//# sourceMappingURL=vite.config.js.map