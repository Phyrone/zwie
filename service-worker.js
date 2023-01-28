const l = [
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/assets/_layout-bc435ffc.css",
  "/_app/immutable/chunks/alerts-67056a1a.js",
  "/_app/immutable/chunks/15-954646be.js",
  "/_app/immutable/chunks/18-47839b3a.js",
  "/_app/immutable/modules/pages/_layout.ts-ee6743a5.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-94291217.js",
  "/_app/immutable/components/pages/_layout.svelte-3314440a.js",
  "/_app/immutable/chunks/update-2dfb1f4a.js",
  "/_app/immutable/chunks/singletons-57d332aa.js",
  "/_app/immutable/chunks/14-ed981e3d.js",
  "/_app/immutable/chunks/preload-helper-41c905a7.js",
  "/_app/immutable/chunks/E-Mail_Change-f9133810.js",
  "/_app/immutable/chunks/NameChange-98c2f288.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-c8bdbe51.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-2fe4ecce.js",
  "/_app/immutable/chunks/0-e05883fa.js",
  "/_app/immutable/chunks/1-1988407f.js",
  "/_app/immutable/chunks/2-f3ddda3b.js",
  "/_app/immutable/chunks/3-87d21227.js",
  "/_app/immutable/chunks/4-7895a345.js",
  "/_app/immutable/chunks/5-83788681.js",
  "/_app/immutable/chunks/6-02d10049.js",
  "/_app/immutable/chunks/theme-selector-78ecbb54.js",
  "/_app/immutable/chunks/7-a0c3d936.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-90c6df9f.js",
  "/_app/immutable/chunks/8-4d7bb033.js",
  "/_app/immutable/chunks/9-ca0a3baf.js",
  "/_app/immutable/chunks/10-72e41c28.js",
  "/_app/immutable/chunks/11-ed4e9d8b.js",
  "/_app/immutable/chunks/12-1c441b8f.js",
  "/_app/immutable/chunks/13-9f313e21.js",
  "/_app/immutable/chunks/_commonjsHelpers-28e086c5.js",
  "/_app/immutable/chunks/_layout-b00c1660.js",
  "/_app/immutable/components/error.svelte-efa1ac71.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-1bc49b69.js",
  "/_app/immutable/chunks/16-6e53b918.js",
  "/_app/immutable/chunks/17-a1f2311e.js",
  "/_app/immutable/chunks/Icon-045404cc.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-184bc4ca.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-dabc7e26.js",
  "/_app/immutable/chunks/index-bd5d3c8f.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-fc603a8d.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-9a58e21e.js",
  "/_app/immutable/start-abdddd45.js",
  "/_app/immutable/chunks/sweetalert2.all-59c9fe1c.js",
  "/_app/immutable/chunks/_page-da46b06b.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-3acb2911.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-9a5fa33c.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-17e04391.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-a1d7691b.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-e2c35919.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-90c6df9f.js",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-38b71d1d.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-1208068f.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-90c6df9f.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-90c6df9f.js",
  "/_app/immutable/chunks/index-240dae6f.js"
], i = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], o = [
  "/"
], p = "1674905063650";
console.log("[ServiceWorker] Init...");
const u = [...l, ...o, ...i], n = "offline::", c = n + p;
let m = caches.open(c);
async function _() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function r() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  let e = -1;
  try {
    let a = await m;
    e = await Promise.all(u.map((s) => {
      a.add(s).catch((t) => {
        console.error(`[ServiceWorker] Failed to cache ${s}`, t);
      });
    })).then((s) => s.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function b(e) {
  let a = await m, s = await a.match(e);
  if (s)
    return s;
  try {
    let t = await fetch(e);
    return await a.put(e, t.clone()), t;
  } catch {
    return await a.match("/") ?? Response.error();
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(r());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(_());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === self.location.host && e.respondWith(b(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
