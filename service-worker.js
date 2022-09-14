const u = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-1ba7643f.js",
  "/_app/immutable/components/pages/_layout.svelte-231fc372.js",
  "/_app/immutable/assets/_layout-5bab0b92.css",
  "/_app/immutable/components/error.svelte-d62e18d8.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-8a89a74c.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-fcf11360.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ad6b3503.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-5ffbbe04.js",
  "/_app/immutable/modules/pages/(main)/s/_layout.ts-e6dc5166.js",
  "/_app/immutable/chunks/singletons-927963de.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-0fd2fd5e.js",
  "/_app/immutable/chunks/_layout-b711fdb6.js",
  "/_app/immutable/chunks/_layout-d81b7549.js",
  "/_app/immutable/chunks/0-e6478a3d.js",
  "/_app/immutable/chunks/1-3dd11f4c.js",
  "/_app/immutable/chunks/2-1efd23f1.js",
  "/_app/immutable/chunks/3-fce019e4.js",
  "/_app/immutable/chunks/4-a4d61399.js",
  "/_app/immutable/chunks/5-23a8fd27.js",
  "/_app/immutable/chunks/6-aae51bed.js",
  "/_app/immutable/chunks/7-b5009314.js",
  "/_app/immutable/chunks/8-49669905.js"
], _ = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], d = [
  "/",
  "/settings"
], s = "1663175445738", f = [...u, ...d, ..._];
console.log("[ServiceWorker] Init...");
const n = "offline::", l = n + s;
let i = caches.open(l), c;
o();
async function r() {
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
    e = await Promise.all(f.map((t) => a.add(t))).then((t) => t.length), await o();
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
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(r());
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
