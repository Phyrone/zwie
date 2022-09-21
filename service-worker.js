const _ = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-4279bedf.js",
  "/_app/immutable/components/pages/_layout.svelte-231fc372.js",
  "/_app/immutable/assets/_layout-78e78c70.css",
  "/_app/immutable/components/error.svelte-8ebcabad.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-c8305d9f.js",
  "/_app/immutable/components/pages/(main)/s/_layout.svelte-70d3384f.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-02e387ad.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ad6b3503.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-018b3e63.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-5ffbbe04.js",
  "/_app/immutable/modules/pages/(main)/s/_layout.ts-e6dc5166.js",
  "/_app/immutable/chunks/singletons-b0654bc4.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-0fd2fd5e.js",
  "/_app/immutable/chunks/_layout-b711fdb6.js",
  "/_app/immutable/chunks/_layout-d81b7549.js",
  "/_app/immutable/chunks/0-e6478a3d.js",
  "/_app/immutable/chunks/1-10c6988f.js",
  "/_app/immutable/chunks/2-fd2456c2.js",
  "/_app/immutable/chunks/3-9705395e.js",
  "/_app/immutable/chunks/4-3f2b3cba.js",
  "/_app/immutable/chunks/5-23a8fd27.js",
  "/_app/immutable/chunks/6-aae51bed.js",
  "/_app/immutable/chunks/7-b5009314.js",
  "/_app/immutable/chunks/8-cdee6e32.js",
  "/_app/immutable/chunks/9-86b2c8fc.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], d = [
  "/",
  "/settings"
], t = "1663797136325", r = [..._, ...d, ...u];
console.log("[ServiceWorker] Init...");
const i = "offline::", c = i + t;
let n = caches.open(c), l;
p();
async function f() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(i) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function b() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${t})`);
  let e = -1;
  try {
    let a = await n;
    e = await Promise.all(r.map((s) => a.add(s))).then((s) => s.length), await p();
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${t})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function p() {
  let e = await n;
  l = await Promise.all(await e.keys().then((a) => a.map((s) => s.url)));
}
async function h(e) {
  let a = await n, s = await a.match(e);
  if (s)
    return s;
  {
    let m = a.add(e), o = fetch(e);
    return await m, await o;
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(b());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(f());
});
addEventListener(
  "fetch",
  async (e) => {
    l.includes(e.request.url) && e.respondWith(h(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
