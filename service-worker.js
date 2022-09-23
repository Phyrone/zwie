const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-efaba288.js",
  "/_app/immutable/components/pages/_layout.svelte-5ee50d1d.js",
  "/_app/immutable/assets/_layout-8b56aacf.css",
  "/_app/immutable/components/error.svelte-ac850209.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-f2b582b9.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-d0db6484.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-529c582e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-afdc6330.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-44f92f9d.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-5b3db852.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-ab44149d.js",
  "/_app/immutable/components/pages/settings/_page.svelte-e3ffc401.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-0702b165.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-30069934.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e631bbe6.js",
  "/_app/immutable/chunks/1-f8d90f75.js",
  "/_app/immutable/chunks/2-50abce49.js",
  "/_app/immutable/chunks/3-c50a8d98.js",
  "/_app/immutable/chunks/4-fb46fdbe.js",
  "/_app/immutable/chunks/5-3f445829.js",
  "/_app/immutable/chunks/6-37ac81e4.js",
  "/_app/immutable/chunks/7-afa3dea5.js",
  "/_app/immutable/chunks/8-1e8cfed0.js",
  "/_app/immutable/chunks/9-faecdfa2.js",
  "/_app/immutable/chunks/10-44d52fdc.js",
  "/_app/immutable/chunks/11-80b9bdea.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], r = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], u = [
  "/"
], c = "1663936690766";
console.log("[ServiceWorker] Init...");
const _ = [...o, ...u, ...r], i = "offline::", l = i + c;
let p = caches.open(l);
const m = self.location.host;
console.debug("[ServiceWorker] host=" + m);
async function d() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(i) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function b() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${c})`);
  let e = -1;
  try {
    let a = await p;
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
async function f(e) {
  var t;
  let a = await p, s = await a.match(e);
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(b());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(d());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === m && e.respondWith(f(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
