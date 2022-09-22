const _ = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-e67a28c2.js",
  "/_app/immutable/components/pages/_layout.svelte-2c49f207.js",
  "/_app/immutable/assets/_layout-78e78c70.css",
  "/_app/immutable/components/error.svelte-30cdc88e.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-1183d5fe.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-02e387ad.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-84d91536.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ad6b3503.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3c2795c4.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-018b3e63.js",
  "/_app/immutable/components/pages/settings/_page.svelte-9347d66a.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-214a58d8.js",
  "/_app/immutable/chunks/index-1eb7d44b.js",
  "/_app/immutable/chunks/index-d12f5de2.js",
  "/_app/immutable/chunks/sweetalert2.all-0fd2fd5e.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-785abb6f.js",
  "/_app/immutable/chunks/1-c496d5e8.js",
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
], d = [
  "/"
], t = "1663842353741", r = [..._, ...d, ...u];
console.log("[ServiceWorker] Init...");
const c = "offline::", i = c + t;
let n = caches.open(i), l;
p();
async function f() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(c) && a !== i)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function h() {
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
async function b(e) {
  let a = await n, s = await a.match(e);
  if (s)
    return s;
  {
    let o = a.add(e), m = fetch(e);
    return await o, await m;
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
