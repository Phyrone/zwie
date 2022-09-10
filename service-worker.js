const _ = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-d24f0246.js",
  "/_app/immutable/components/pages/_layout.svelte-7d3207f1.js",
  "/_app/immutable/assets/_layout-eb5d6723.css",
  "/_app/immutable/components/error.svelte-c198f362.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-8155a57c.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-1be255f6.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-ebc2269d.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-d224d535.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-dd63093f.js",
  "/_app/immutable/components/pages/settings/_page.svelte-056ada1f.js",
  "/_app/immutable/modules/pages/_layout.ts-b8ee4d7c.js",
  "/_app/immutable/modules/pages/(main)/s/_layout.ts-82dbd318.js",
  "/_app/immutable/chunks/singletons-9e33c3b9.js",
  "/_app/immutable/chunks/index-6443e61f.js",
  "/_app/immutable/chunks/index-8b1f785e.js",
  "/_app/immutable/chunks/sweetalert2.all-4e992e51.js",
  "/_app/immutable/chunks/_layout-1daba58d.js",
  "/_app/immutable/chunks/_layout-05547402.js",
  "/_app/immutable/chunks/0-b4a92a55.js",
  "/_app/immutable/chunks/1-3bbcc37a.js",
  "/_app/immutable/chunks/2-ce526338.js",
  "/_app/immutable/chunks/3-ea14e688.js",
  "/_app/immutable/chunks/4-364b1256.js",
  "/_app/immutable/chunks/5-c1e9e73f.js",
  "/_app/immutable/chunks/6-ef7db0d8.js",
  "/_app/immutable/chunks/7-60256c9d.js",
  "/_app/immutable/chunks/8-36c36dd9.js"
], u = [
  "/manifest.webmanifest"
], r = [
  "/",
  "/settings"
], s = "1662851544032", d = [..._, ...r, ...u];
console.log("[ServiceWorker] Init...");
const n = "offline::", l = n + s;
let i = caches.open(l), c;
o();
async function f() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function h() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${s})`);
  let e = -1;
  try {
    let a = await i;
    e = await Promise.all(d.map((t) => a.add(t))).then((t) => t.length), await o();
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${s})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function o() {
  let e = await i;
  c = await Promise.all(await e.keys().then((a) => a.map((t) => t.url)));
}
async function b(e) {
  let a = await i, t = await a.match(e);
  if (t)
    return t;
  {
    let p = a.add(e), m = fetch(e);
    return await p, await m;
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(h());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(f());
});
addEventListener(
  "fetch",
  async (e) => {
    c.includes(e.request.url) && e.respondWith(b(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
