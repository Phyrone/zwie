const i = [
  "/_app/immutable/chunks/0-dba24ee1.js",
  "/_app/immutable/chunks/1-4e856669.js",
  "/_app/immutable/chunks/10-534a0a81.js",
  "/_app/immutable/chunks/11-23a952fb.js",
  "/_app/immutable/chunks/12-823e6bdf.js",
  "/_app/immutable/chunks/13-99f60c66.js",
  "/_app/immutable/chunks/14-60999bd4.js",
  "/_app/immutable/chunks/15-b9f71fde.js",
  "/_app/immutable/chunks/16-bfa7b1a8.js",
  "/_app/immutable/chunks/17-5536941f.js",
  "/_app/immutable/chunks/18-bfbe0aa7.js",
  "/_app/immutable/chunks/2-0bee9d3b.js",
  "/_app/immutable/chunks/3-d7ab1036.js",
  "/_app/immutable/chunks/4-d07c6912.js",
  "/_app/immutable/chunks/5-7644cdfc.js",
  "/_app/immutable/chunks/6-a2fafacc.js",
  "/_app/immutable/chunks/7-4fddeebf.js",
  "/_app/immutable/chunks/8-8beca359.js",
  "/_app/immutable/chunks/9-958c9a86.js",
  "/_app/immutable/chunks/Icon-a754690e.js",
  "/_app/immutable/chunks/_commonjsHelpers-edff4021.js",
  "/_app/immutable/chunks/_layout-b00c1660.js",
  "/_app/immutable/chunks/_page-da46b06b.js",
  "/_app/immutable/chunks/alerts-d6284e64.js",
  "/_app/immutable/chunks/index-3b672fbb.js",
  "/_app/immutable/chunks/index-b585bed5.js",
  "/_app/immutable/chunks/localforage-e8653f69.js",
  "/_app/immutable/chunks/preload-helper-41c905a7.js",
  "/_app/immutable/chunks/singletons-fb12cd40.js",
  "/_app/immutable/chunks/sweetalert2.all-b59b2082.js",
  "/_app/immutable/chunks/theme-selector-bbacf5b5.js",
  "/_app/immutable/chunks/update-e75f62cf.js",
  "/_app/immutable/start-7f541f7a.js",
  "/_app/immutable/components/error.svelte-18155622.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-8ada13cd.js",
  "/_app/immutable/chunks/E-Mail_Change-91753120.js",
  "/_app/immutable/chunks/NameChange-a0ada6a5.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-80239f09.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-95664ef8.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-94291217.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-6ede0a1d.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-dd23162b.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-6ede0a1d.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-8812149d.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-b8e92903.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-2f1590cf.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-5dcbb1a8.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-3ec9e764.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-6ede0a1d.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-f15c35ea.js",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-f5add03c.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-2d39a5d3.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-086f3e92.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-997bc535.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-6ede0a1d.js",
  "/_app/immutable/assets/_layout-bc435ffc.css",
  "/_app/immutable/components/pages/_layout.svelte-79f2dbdf.js",
  "/_app/immutable/modules/pages/_layout.ts-ee6743a5.js"
], c = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], o = [
  "/"
], p = "1675463680328";
console.log("[ServiceWorker] Init...");
const u = [...i, ...o, ...c], n = "offline::", m = n + p;
let l = caches.open(m);
async function _() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== m)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function b() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  let e = -1;
  try {
    let a = await l;
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
  let a = await l, s = await a.match(e);
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
