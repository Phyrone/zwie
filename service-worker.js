const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-612a23d2.js",
  "/_app/immutable/components/pages/_layout.svelte-288baa76.js",
  "/_app/immutable/assets/_layout-9e650cc4.css",
  "/_app/immutable/components/error.svelte-755ff447.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-84cf687c.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-69decf83.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-14d4ba50.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-a47ed18e.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-87093681.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-6e60f75c.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-65d7b9e3.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-4b47a41b.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-57dd8361.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-4e9b2b2b.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-1c03e75e.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-fcd642e2.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-5ad93f62.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-00fab7b2.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-b64d861f.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-4a6983ee.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-5f54bbfc.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/index-b31b03a2.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-93f16b68.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-16ef5bb2.js",
  "/_app/immutable/chunks/1-5dae0455.js",
  "/_app/immutable/chunks/2-c93610dc.js",
  "/_app/immutable/chunks/3-179f9a89.js",
  "/_app/immutable/chunks/4-f5c6524d.js",
  "/_app/immutable/chunks/5-65947d4e.js",
  "/_app/immutable/chunks/6-367ff65f.js",
  "/_app/immutable/chunks/7-8ce4c0d6.js",
  "/_app/immutable/chunks/8-29f8b80e.js",
  "/_app/immutable/chunks/9-14da90ad.js",
  "/_app/immutable/chunks/10-8dbb7893.js",
  "/_app/immutable/chunks/11-d35d81c5.js",
  "/_app/immutable/chunks/12-a0dee7fb.js",
  "/_app/immutable/chunks/13-d7d2d957.js",
  "/_app/immutable/chunks/14-81d21a21.js",
  "/_app/immutable/chunks/15-2cf59244.js",
  "/_app/immutable/chunks/16-7ece2be7.js",
  "/_app/immutable/chunks/17-e706973d.js",
  "/_app/immutable/chunks/18-712b378e.js",
  "/_app/immutable/chunks/19-64250edc.js",
  "/_app/immutable/chunks/E-Mail_Change-6e886ce8.js",
  "/_app/immutable/chunks/NameChange-3c69b932.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1664310269183";
console.log("[ServiceWorker] Init...");
const b = [...o, ..._, ...u], m = "offline::", c = m + n;
let i = caches.open(c);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function r() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
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
