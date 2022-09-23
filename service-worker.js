const m = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-e76795c8.js",
  "/_app/immutable/components/pages/_layout.svelte-5ee50d1d.js",
  "/_app/immutable/assets/_layout-7506e37b.css",
  "/_app/immutable/components/error.svelte-05a84124.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-f2b582b9.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-d0db6484.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-529c582e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-afdc6330.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-1f1d4cc0.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-5b3db852.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-ab44149d.js",
  "/_app/immutable/components/pages/settings/_page.svelte-e3ffc401.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-22728c06.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-30069934.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e631bbe6.js",
  "/_app/immutable/chunks/1-e10c288b.js",
  "/_app/immutable/chunks/2-50abce49.js",
  "/_app/immutable/chunks/3-c50a8d98.js",
  "/_app/immutable/chunks/4-fb46fdbe.js",
  "/_app/immutable/chunks/5-3f445829.js",
  "/_app/immutable/chunks/6-42f20220.js",
  "/_app/immutable/chunks/7-638faae2.js",
  "/_app/immutable/chunks/8-b3d53480.js",
  "/_app/immutable/chunks/9-a62f2d69.js"
], r = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], u = [
  "/"
], c = "1663896740043";
console.log("[ServiceWorker] Init...");
const _ = [...m, ...u, ...r], i = "offline::", l = i + c;
let o = caches.open(l);
const p = self.location.host;
console.debug("[ServiceWorker] host=" + p);
async function d() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(i) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function f() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${c})`);
  let e = -1;
  try {
    let a = await o;
    e = await Promise.all(_.map((s) => {
      a.add(s).catch((t) => {
        console.error(`[ServiceWorker] Failed to cache ${s}`, t);
      });
    })).then((s) => s.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${c})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function b(e) {
  var t;
  let a = await o, s = await a.match(e);
  if (s)
    return s;
  try {
    let n = await fetch(e);
    return await a.put(e, n.clone()), n;
  } catch {
    return (t = await a.match("/")) != null ? t : Response.error();
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(f());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(d());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === p && e.respondWith(b(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
