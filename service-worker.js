const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-29bb1ec9.js",
  "/_app/immutable/components/pages/_layout.svelte-5ee50d1d.js",
  "/_app/immutable/assets/_layout-e2955df0.css",
  "/_app/immutable/components/error.svelte-b43636cc.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-950a603d.js",
  "/_app/immutable/components/pages/(main)/Modus/_layout.svelte-f0ec9228.js",
  "/_app/immutable/components/pages/(main)/Plugins/_layout.svelte-ca61074b.js",
  "/_app/immutable/components/pages/(main)/Settings/_layout.svelte-ee540d03.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-a2be4ce9.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-529c582e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-afdc6330.js",
  "/_app/immutable/components/pages/(main)/Modus/_page.svelte-98976bd4.js",
  "/_app/immutable/components/pages/(main)/Plugins/_page.svelte-8fd01ded.js",
  "/_app/immutable/components/pages/(main)/Settings/_page.svelte-2c1d7065.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-db51f798.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3fdfc2ab.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-ab44149d.js",
  "/_app/immutable/components/pages/settings/_page.svelte-e3ffc401.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-34c6b20b.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-30069934.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e631bbe6.js",
  "/_app/immutable/chunks/1-fecaaea4.js",
  "/_app/immutable/chunks/2-fb2e4c04.js",
  "/_app/immutable/chunks/3-de8c366d.js",
  "/_app/immutable/chunks/4-833e7532.js",
  "/_app/immutable/chunks/5-8da43a59.js",
  "/_app/immutable/chunks/6-db8fb131.js",
  "/_app/immutable/chunks/7-8e1c5aa7.js",
  "/_app/immutable/chunks/8-1ead62e7.js",
  "/_app/immutable/chunks/9-48585d74.js",
  "/_app/immutable/chunks/10-7d8c2c5d.js",
  "/_app/immutable/chunks/11-706f8869.js",
  "/_app/immutable/chunks/12-98c8e9a0.js",
  "/_app/immutable/chunks/13-3e26aaac.js",
  "/_app/immutable/chunks/14-7e66d539.js",
  "/_app/immutable/chunks/15-f0a7655b.js",
  "/_app/immutable/chunks/16-6ea64c44.js",
  "/_app/immutable/chunks/17-f0836f18.js",
  "/_app/immutable/chunks/E-Mail_Change-6e886ce8.js",
  "/_app/immutable/chunks/NameChange-3c69b932.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], p = "1664046405801";
console.log("[ServiceWorker] Init...");
const r = [...o, ..._, ...u], c = "offline::", l = c + p;
let m = caches.open(l);
const i = self.location.host;
console.debug("[ServiceWorker] host=" + i);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(c) && a !== l)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function d() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  let e = -1;
  try {
    let a = await m;
    e = await Promise.all(r.map((s) => {
      a.add(s).catch((t) => {
        console.error(`[ServiceWorker] Failed to cache ${s}`, t);
      });
    })).then((s) => s.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function h(e) {
  var t;
  let a = await m, s = await a.match(e);
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
    new URL(e.request.url).host === i && e.respondWith(h(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
