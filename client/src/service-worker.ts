import {version, build, prerendered, files} from '$service-worker'

const static_files: string[] = [...build, ...prerendered, ...files];
console.log('[ServiceWorker] Init...')
const offline_cache_name_prefix = 'offline::'
const offline_cache_name = offline_cache_name_prefix + version
let offline_cache_promise = caches.open(offline_cache_name);
let offline_cache_keys: string[];

// noinspection JSIgnoredPromiseFromCall
pupulate_offline_cache_keys();

async function gc_cache() {
    try {
        console.time('[ServiceWorker] deleting old caches')
        await Promise.all(await caches.keys()
            .then((cache_keys) => cache_keys.filter((key) => key.startsWith(offline_cache_name_prefix) && key !== offline_cache_name))
            .then((cache_keys) => cache_keys.map((cache_key) => caches.delete(cache_key))))

    } finally {
        console.timeEnd('[ServiceWorker] deleting old caches')
    }

}

async function warm_cache() {


    console.time(`[ServiceWorker] Creating/Updating Cache... (${version})`)
    let cache_size: number = -1;
    try {
        let offline_cache = await offline_cache_promise;
        cache_size = await Promise.all(static_files.map((file) => offline_cache.add(file))).then((tasks) => tasks.length)
        await pupulate_offline_cache_keys()
    } finally {
        console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${version})`)
    }
    console.log(`[ServiceWorker] Added ${cache_size} files to cache`)
}

async function pupulate_offline_cache_keys() {
    let offline_cache = await offline_cache_promise;
    offline_cache_keys = await Promise.all(await offline_cache.keys().then((keys) => keys.map((key) => key.url)))
}

async function offline_first_response(request: Request): Promise<Response> {
    let offline_cache = await offline_cache_promise;
    let cached = await offline_cache.match(request)
    if (cached) {
        return cached
    } else {
        let cache_add = offline_cache.add(request)
        let fetched = fetch(request)
        await cache_add
        return await fetched
    }


}

addEventListener('install', (event) => {
    console.log('[ServiceWorker] Install Event...')
    event.waitUntil(warm_cache())
})

addEventListener('activate', (event) => {
    console.log('[ServiceWorker] Activate Event...')
    event.waitUntil(gc_cache())
})

addEventListener('fetch', async (event) => {

        if (offline_cache_keys.includes(event.request.url)) {

            event.respondWith(offline_first_response(event.request))
        }

    }
)
self.addEventListener('message', (event) => {
    if (event.data === 'APPLY_UPDATE') {
        self.skipWaiting();

    }
});
