const _ = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-eacf4b27.js",
  "/_app/immutable/components/pages/_layout.svelte-8d6c50e3.js",
  "/_app/immutable/assets/_layout-eb5d6723.css",
  "/_app/immutable/components/error.svelte-710deea4.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-bd99c120.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-fcf11360.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-52b734e3.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-b8ee4d7c.js",
  "/_app/immutable/modules/pages/(main)/s/_layout.ts-82dbd318.js",
  "/_app/immutable/chunks/singletons-2bd98263.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-9310e954.js",
  "/_app/immutable/chunks/_layout-1daba58d.js",
  "/_app/immutable/chunks/_layout-05547402.js",
  "/_app/immutable/chunks/0-d6a785b2.js",
  "/_app/immutable/chunks/1-ee610b10.js",
  "/_app/immutable/chunks/2-9334b45d.js",
  "/_app/immutable/chunks/3-ea14e688.js",
  "/_app/immutable/chunks/4-a4d61399.js",
  "/_app/immutable/chunks/5-23a8fd27.js",
  "/_app/immutable/chunks/6-6185cffd.js",
  "/_app/immutable/chunks/7-b5009314.js",
  "/_app/immutable/chunks/8-49669905.js"
], u = [
  "/manifest.webmanifest"
], d = [
  "/",
  "/settings"
], s = "1662853461455", r = [..._, ...d, ...u];
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
    e = await Promise.all(r.map((t) => a.add(t))).then((t) => t.length), await o();
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
