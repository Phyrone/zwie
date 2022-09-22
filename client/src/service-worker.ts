import {version, build, prerendered, files} from '$service-worker'

console.log('[ServiceWorker] Init...')

const static_files: string[] = [...build, ...prerendered, ...files]
const offline_cache_name_prefix = 'offline::'
const offline_cache_name = offline_cache_name_prefix + version
let offline_cache_promise = caches.open(offline_cache_name);
const current_host = self.location.host;
console.debug("[ServiceWorker] host=" + current_host);

async function cleanup_old_caches() {
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
        cache_size = await Promise.all(static_files.map((file) => {
            offline_cache.add(file).catch((e) => {
                console.error(`[ServiceWorker] Failed to cache ${file}`, e)
            })
        })).then((tasks) => tasks.length)
    } finally {
        console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${version})`)
    }
    console.log(`[ServiceWorker] Added ${cache_size} files to cache`)
}

async function offline_first_response(request: Request): Promise<Response> {
    let offline_cache = await offline_cache_promise;
    let cached = await offline_cache.match(request)
    if (cached) {
        return cached
    } else {
        let cache_add_promise = offline_cache.add(request)

        let fetched_promise = fetch(request)
        await cache_add_promise
        let fetched = await fetched_promise

        if (fetched.ok) {
            return fetched
        } else {
            return await offline_cache.match("/") ?? fetched
        }
    }
}

addEventListener('install', (event) => {
    console.log('[ServiceWorker] Install Event...')
    event.waitUntil(warm_cache())
})

addEventListener('activate', (event) => {
    console.log('[ServiceWorker] Activate Event...')
    event.waitUntil(cleanup_old_caches())
})

addEventListener('fetch', async (event) => {

        let url = new URL(event.request.url)

        if (url.host === current_host) {
            event.respondWith(offline_first_response(event.request))
        }

    }
)
self.addEventListener('message', (event) => {
    if (event.data === 'APPLY_UPDATE') {
        self.skipWaiting();

    }
});
