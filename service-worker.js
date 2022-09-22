const d = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-e3443c7b.js",
  "/_app/immutable/components/pages/_layout.svelte-2c49f207.js",
  "/_app/immutable/assets/_layout-78e78c70.css",
  "/_app/immutable/components/error.svelte-2ba47bc6.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-1183d5fe.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-02e387ad.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ad6b3503.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-018b3e63.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-fde810e3.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-0fd2fd5e.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-785abb6f.js",
  "/_app/immutable/chunks/1-2da875f7.js",
  "/_app/immutable/chunks/2-9dd3f7b6.js",
  "/_app/immutable/chunks/3-ca9234c4.js",
  "/_app/immutable/chunks/4-9e67cbbf.js",
  "/_app/immutable/chunks/5-4114ca2a.js",
  "/_app/immutable/chunks/6-29b81c71.js",
  "/_app/immutable/chunks/7-a1ab3bd5.js",
  "/_app/immutable/chunks/8-49669905.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], f = [
  "/"
], n = "1663855060529";
console.log("[ServiceWorker] Init...");
const h = [...d, ...f, ...u], i = "offline::", l = i + n;
let o = caches.open(l);
const p = self.location.host;
console.debug("[ServiceWorker] host=" + p);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(i) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function g() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await o;
    e = await Promise.all(h.map((t) => {
      a.add(t).catch((s) => {
        console.error(`[ServiceWorker] Failed to cache ${t}`, s);
      });
    })).then((t) => t.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function v(e) {
  var s;
  let a = await o, t = await a.match(e);
  if (t)
    return t;
  {
    let m = a.add(e), r = fetch(e);
    try {
      await m;
    } catch (_) {
      console.error(`[ServiceWorker] Failed to cache ${e.url}`, _);
    }
    let c = await r;
    return c.ok ? c : (s = await a.match("/")) != null ? s : c;
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(g());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(b());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === p && e.respondWith(v(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
