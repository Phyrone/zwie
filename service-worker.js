const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-bc91ccf2.js",
  "/_app/immutable/components/pages/_layout.svelte-bece6393.js",
  "/_app/immutable/assets/_layout-459fe561.css",
  "/_app/immutable/components/error.svelte-b07ec387.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-61d2cf65.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-17450b86.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-b73916d2.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-42d1143f.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-178f3e19.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-00440828.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-3b33fd40.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-c4c7319d.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-829cdfab.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-9fa896ff.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-530bcfe2.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-182516ea.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-50112e17.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-bcae7e23.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-1e378e39.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-30dfca51.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-d5d0d1c2.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-769cfce9.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-806cd8cb.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-46970648.js",
  "/_app/immutable/chunks/index-bc01506a.js",
  "/_app/immutable/chunks/theme-selector-7a652417.js",
  "/_app/immutable/chunks/messages-9145f738.js",
  "/_app/immutable/chunks/sweetalert2.all-04665922.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-df2bb7a6.js",
  "/_app/immutable/chunks/1-e3c701af.js",
  "/_app/immutable/chunks/2-211d429a.js",
  "/_app/immutable/chunks/3-8ac24845.js",
  "/_app/immutable/chunks/4-1bc2b554.js",
  "/_app/immutable/chunks/5-a10657d3.js",
  "/_app/immutable/chunks/6-b535f312.js",
  "/_app/immutable/chunks/7-411a8874.js",
  "/_app/immutable/chunks/8-9b54694d.js",
  "/_app/immutable/chunks/9-29769497.js",
  "/_app/immutable/chunks/10-f8d033a5.js",
  "/_app/immutable/chunks/11-ca33afaa.js",
  "/_app/immutable/chunks/12-3fb3d434.js",
  "/_app/immutable/chunks/13-0d83decf.js",
  "/_app/immutable/chunks/14-c032f1b2.js",
  "/_app/immutable/chunks/15-ae200b38.js",
  "/_app/immutable/chunks/16-136a1bd5.js",
  "/_app/immutable/chunks/17-1b4426fe.js",
  "/_app/immutable/chunks/18-79c93c26.js",
  "/_app/immutable/chunks/19-4b192531.js",
  "/_app/immutable/chunks/E-Mail_Change-78c1f71b.js",
  "/_app/immutable/chunks/NameChange-d55b314f.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-74ce1421.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1666381636595";
console.log("[ServiceWorker] Init...");
const b = [...o, ..._, ...u], c = "offline::", m = c + n;
let i = caches.open(m);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function r() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(c) && a !== m)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function d() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await i;
    e = await Promise.all(b.map((s) => {
      a.add(s).catch((t) => {
        console.error(`[ServiceWorker] Failed to cache ${s}`, t);
      });
    })).then((s) => s.length);
  } finally {
    console.timeEnd(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  }
  console.log(`[ServiceWorker] Added ${e} files to cache`);
}
async function h(e) {
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(d());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(r());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === l && e.respondWith(h(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
