const i = [
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/assets/_layout-bc435ffc.css",
  "/_app/immutable/chunks/singletons-36dca951.js",
  "/_app/immutable/chunks/index-7a739a2d.js",
  "/_app/immutable/chunks/update-70f373a7.js",
  "/_app/immutable/chunks/preload-helper-41c905a7.js",
  "/_app/immutable/chunks/_commonjsHelpers-28e086c5.js",
  "/_app/immutable/chunks/index-1e50f43e.js",
  "/_app/immutable/start-8779cb4a.js",
  "/_app/immutable/chunks/theme-selector-cc60be6b.js",
  "/_app/immutable/chunks/0-b22fe701.js",
  "/_app/immutable/chunks/1-8be35a02.js",
  "/_app/immutable/chunks/2-ccbc305f.js",
  "/_app/immutable/chunks/3-bd8a9645.js",
  "/_app/immutable/chunks/4-438f8eef.js",
  "/_app/immutable/chunks/6-e3659293.js",
  "/_app/immutable/chunks/7-97c5ccdf.js",
  "/_app/immutable/chunks/8-0d724942.js",
  "/_app/immutable/chunks/9-20052ac8.js",
  "/_app/immutable/chunks/10-0a879021.js",
  "/_app/immutable/chunks/11-9f5d238c.js",
  "/_app/immutable/chunks/12-3de6b948.js",
  "/_app/immutable/chunks/5-c4117fdb.js",
  "/_app/immutable/chunks/13-6f472a0c.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-24df01a3.js",
  "/_app/immutable/chunks/14-58354f12.js",
  "/_app/immutable/chunks/15-2493aac5.js",
  "/_app/immutable/chunks/16-dde548c5.js",
  "/_app/immutable/chunks/sweetalert2.all-59c9fe1c.js",
  "/_app/immutable/chunks/17-4acc9d33.js",
  "/_app/immutable/chunks/18-51a10d38.js",
  "/_app/immutable/modules/pages/_layout.ts-ee6743a5.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-94291217.js",
  "/_app/immutable/chunks/_layout-b00c1660.js",
  "/_app/immutable/components/error.svelte-c3544f50.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-bff6a00d.js",
  "/_app/immutable/components/pages/_layout.svelte-12ec0bfa.js",
  "/_app/immutable/chunks/Icon-bfde868f.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-a58594d1.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-5c8e5e60.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-f26f9408.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-5ab5ab38.js",
  "/_app/immutable/chunks/_page-da46b06b.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-f478b715.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-f478b715.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-dea2b20f.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-c45c13a2.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-b4a8b155.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-f478b715.js",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-6da3912b.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-81ce5362.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-73547b9a.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-f478b715.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-f1855ab6.js",
  "/_app/immutable/chunks/E-Mail_Change-5775457b.js",
  "/_app/immutable/chunks/NameChange-6479f9c6.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-cf9957cc.js"
], l = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], o = [
  "/"
], p = "1674855538842";
console.log("[ServiceWorker] Init...");
const u = [...i, ...o, ...l], n = "offline::", c = n + p;
let m = caches.open(c);
async function _() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function b() {
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
async function r(e) {
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(b());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(_());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === self.location.host && e.respondWith(r(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
