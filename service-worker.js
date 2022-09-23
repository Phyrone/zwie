const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-45bce31a.js",
  "/_app/immutable/components/pages/_layout.svelte-5ee50d1d.js",
  "/_app/immutable/assets/_layout-e2955df0.css",
  "/_app/immutable/components/error.svelte-f22f2a8d.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-f2b582b9.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-a2be4ce9.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-529c582e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-afdc6330.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-db51f798.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3fdfc2ab.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-ab44149d.js",
  "/_app/immutable/components/pages/settings/_page.svelte-e3ffc401.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-da47a019.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-30069934.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e631bbe6.js",
  "/_app/immutable/chunks/1-8b25799a.js",
  "/_app/immutable/chunks/2-50abce49.js",
  "/_app/immutable/chunks/3-61cba6b3.js",
  "/_app/immutable/chunks/4-fb46fdbe.js",
  "/_app/immutable/chunks/5-3f445829.js",
  "/_app/immutable/chunks/6-37ac81e4.js",
  "/_app/immutable/chunks/7-afa3dea5.js",
  "/_app/immutable/chunks/8-1b027ca6.js",
  "/_app/immutable/chunks/9-5901e6a8.js",
  "/_app/immutable/chunks/10-44d52fdc.js",
  "/_app/immutable/chunks/11-80b9bdea.js",
  "/_app/immutable/chunks/E-Mail_Change-6e886ce8.js",
  "/_app/immutable/chunks/NameChange-3c69b932.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], r = [
  "/"
], c = "1663958010924";
console.log("[ServiceWorker] Init...");
const _ = [...o, ...r, ...u], i = "offline::", l = i + c;
let p = caches.open(l);
const m = self.location.host;
console.debug("[ServiceWorker] host=" + m);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(i) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function d() {
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
async function h(e) {
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(d());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(b());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === m && e.respondWith(h(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
