const u = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-5d749eb1.js",
  "/_app/immutable/components/pages/_layout.svelte-231fc372.js",
  "/_app/immutable/assets/_layout-8625eceb.css",
  "/_app/immutable/components/error.svelte-938d2cc7.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-c8305d9f.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-fcf11360.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ad6b3503.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-5ffbbe04.js",
  "/_app/immutable/modules/pages/(main)/s/_layout.ts-e6dc5166.js",
  "/_app/immutable/chunks/singletons-33dce7ac.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-0fd2fd5e.js",
  "/_app/immutable/chunks/_layout-b711fdb6.js",
  "/_app/immutable/chunks/_layout-d81b7549.js",
  "/_app/immutable/chunks/0-e6478a3d.js",
  "/_app/immutable/chunks/1-20abd1a8.js",
  "/_app/immutable/chunks/2-fd2456c2.js",
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
], s = "1663533269775", r = [...u, ...d, ..._];
console.log("[ServiceWorker] Init...");
const n = "offline::", c = n + s;
let i = caches.open(c), l;
o();
async function f() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
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
  l = await Promise.all(await e.keys().then((a) => a.map((t) => t.url)));
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
    l.includes(e.request.url) && e.respondWith(b(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
