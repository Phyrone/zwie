const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-98d9dd72.js",
  "/_app/immutable/components/pages/_layout.svelte-e113e525.js",
  "/_app/immutable/assets/_layout-e002bbc2.css",
  "/_app/immutable/components/error.svelte-9c849339.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-2fc3f808.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-eff5e340.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-a309595c.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-6558d2fb.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-700ca416.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-7d4b350d.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-3b33fd40.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-4f9420f7.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-263a3281.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-5fcb2a10.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-19617a80.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-a0501fc6.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-a1d44145.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-5d29e7e9.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-687d315e.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-08a271cc.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-286d36b2.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-fb1b2a41.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-9bcd2cc7.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-46970648.js",
  "/_app/immutable/chunks/index-bc01506a.js",
  "/_app/immutable/chunks/theme-selector-7a652417.js",
  "/_app/immutable/chunks/messages-9145f738.js",
  "/_app/immutable/chunks/sweetalert2.all-73918ca7.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e7ed5af6.js",
  "/_app/immutable/chunks/1-1505522e.js",
  "/_app/immutable/chunks/2-cf493d06.js",
  "/_app/immutable/chunks/3-b94c73ba.js",
  "/_app/immutable/chunks/4-22383c7b.js",
  "/_app/immutable/chunks/5-ca942d3e.js",
  "/_app/immutable/chunks/6-1be27d27.js",
  "/_app/immutable/chunks/7-17b5c289.js",
  "/_app/immutable/chunks/8-9b54694d.js",
  "/_app/immutable/chunks/9-85fe252a.js",
  "/_app/immutable/chunks/10-c95fb5b9.js",
  "/_app/immutable/chunks/11-5620c91e.js",
  "/_app/immutable/chunks/12-16657f50.js",
  "/_app/immutable/chunks/13-316b8575.js",
  "/_app/immutable/chunks/14-09c45082.js",
  "/_app/immutable/chunks/15-ff452ff9.js",
  "/_app/immutable/chunks/16-4ce78513.js",
  "/_app/immutable/chunks/17-22b7aa83.js",
  "/_app/immutable/chunks/18-80f0f14d.js",
  "/_app/immutable/chunks/19-a036449f.js",
  "/_app/immutable/chunks/E-Mail_Change-602edb7f.js",
  "/_app/immutable/chunks/NameChange-a4602b38.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-71aecb53.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1665713867202";
console.log("[ServiceWorker] Init...");
const r = [...o, ..._, ...u], m = "offline::", c = m + n;
let i = caches.open(c);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function h() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await i;
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
  let a = await i, s = await a.match(e);
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
