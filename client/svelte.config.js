import adapter from '@sveltejs/adapter-static';
import preprocess from 'svelte-preprocess';


/** @type {import('@sveltejs/kit').Config} */
const config = {
    // Consult https://github.com/sveltejs/svelte-preprocess
    // for more information about preprocessors
    preprocess: preprocess({
        postcss: true
    }),

    kit: {

        adapter: adapter({
            fallback: 'index.html',
            pages: 'build',
        }),
        trailingSlash: "ignore",
        serviceWorker: {
            register: false,
        }
    },
    compilerOptions: {
        enableSourcemap: {
            css: true,
            js: true
        }
    },
};

export default config;
