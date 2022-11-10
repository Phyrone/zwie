const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-a724167a.js",
  "/_app/immutable/components/pages/_layout.svelte-b3643a5f.js",
  "/_app/immutable/assets/_layout-efac16e9.css",
  "/_app/immutable/components/error.svelte-c067d248.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-0366af13.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-0688ade5.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-0d2e1742.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-09967a76.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-ae240612.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-b8561271.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-32b08d5e.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-b2097536.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-921f5953.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-c18dd0bd.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-f9199a68.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-326a6431.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-f458de74.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-9b986963.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-3d998db2.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-de11400b.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-1cf1ee58.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-b2a7a353.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-93264467.js",
  "/_app/immutable/chunks/index-5a6d03c0.js",
  "/_app/immutable/chunks/index-c9e3a8b1.js",
  "/_app/immutable/chunks/preload-helper-b21cceae.js",
  "/_app/immutable/chunks/theme-selector-78bffc28.js",
  "/_app/immutable/chunks/messages-002a3112.js",
  "/_app/immutable/chunks/sweetalert2.all-652a4ed8.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e107c505.js",
  "/_app/immutable/chunks/1-a7efaeb2.js",
  "/_app/immutable/chunks/2-923da808.js",
  "/_app/immutable/chunks/3-b28acf6a.js",
  "/_app/immutable/chunks/4-7ba25764.js",
  "/_app/immutable/chunks/5-0c616be6.js",
  "/_app/immutable/chunks/6-99398edc.js",
  "/_app/immutable/chunks/7-18f5cc2c.js",
  "/_app/immutable/chunks/8-11917eab.js",
  "/_app/immutable/chunks/9-59437046.js",
  "/_app/immutable/chunks/10-2e126455.js",
  "/_app/immutable/chunks/11-8377ccd5.js",
  "/_app/immutable/chunks/12-1210fe86.js",
  "/_app/immutable/chunks/13-2afcea64.js",
  "/_app/immutable/chunks/14-0981286e.js",
  "/_app/immutable/chunks/15-a1c89088.js",
  "/_app/immutable/chunks/16-0ff8d570.js",
  "/_app/immutable/chunks/17-709c21cb.js",
  "/_app/immutable/chunks/18-0b91ba99.js",
  "/_app/immutable/chunks/19-1be0d6e0.js",
  "/_app/immutable/chunks/E-Mail_Change-50e70986.js",
  "/_app/immutable/chunks/NameChange-f7da7dae.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-5851c919.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1668068614711";
console.log("[ServiceWorker] Init...");
const r = [...o, ..._, ...u], m = "offline::", i = m + n;
let c = caches.open(i);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== i)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function h() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await c;
    e = await Promise.all(r.map((s) => {
      a.add(s).catch((t) => {
        console.error(`[ServiceWorker] Failed to cache ${s}`, t);
      });
    })).then((s) => s.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function d(e) {
  var t;
  let a = await c, s = await a.match(e);
  if (s)
    return s;
  try {
    let p = await fetch(e);
    return await a.put(e, p.clone()), p;
  } catch {
    return (t = await a.match("/")) != null ? t : Response.error();
  }
}
addEventListener("install", (e) => {
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(h());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(b());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === l && e.respondWith(d(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
