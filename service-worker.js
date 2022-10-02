const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-a9b3792f.js",
  "/_app/immutable/components/pages/_layout.svelte-bfb42137.js",
  "/_app/immutable/assets/_layout-e002bbc2.css",
  "/_app/immutable/components/error.svelte-8d5ab859.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-b208c58f.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-7d06cb6d.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-db8fc76c.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-a0d63191.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-d43f4d58.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-4a4226d5.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-9a176f25.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-95c2be15.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-f9d9a70f.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-6e12545c.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-7495eec7.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-cb829131.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-984bf91e.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-02882efe.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-6ac5c85b.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-9e31f71b.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-bac5a7cf.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-633129c6.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-f5fbf1bb.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-44f1b790.js",
  "/_app/immutable/chunks/index-f2759465.js",
  "/_app/immutable/chunks/theme-selector-8c75a6e6.js",
  "/_app/immutable/chunks/messages-b65a77d2.js",
  "/_app/immutable/chunks/sweetalert2.all-0de9ecb5.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-50efaab9.js",
  "/_app/immutable/chunks/1-84a04c2b.js",
  "/_app/immutable/chunks/2-08588ace.js",
  "/_app/immutable/chunks/3-5f3c89b6.js",
  "/_app/immutable/chunks/4-378ab5cc.js",
  "/_app/immutable/chunks/5-de57fc9b.js",
  "/_app/immutable/chunks/6-1e8b4a8b.js",
  "/_app/immutable/chunks/7-3e7b4324.js",
  "/_app/immutable/chunks/8-6e9066ab.js",
  "/_app/immutable/chunks/9-21b369f6.js",
  "/_app/immutable/chunks/10-28c0ccc1.js",
  "/_app/immutable/chunks/11-9de89728.js",
  "/_app/immutable/chunks/12-7b1acc51.js",
  "/_app/immutable/chunks/13-8b1b620a.js",
  "/_app/immutable/chunks/14-9e18f26c.js",
  "/_app/immutable/chunks/15-f416a260.js",
  "/_app/immutable/chunks/16-c2b9d98f.js",
  "/_app/immutable/chunks/17-7f4adfcb.js",
  "/_app/immutable/chunks/18-c9b3fd08.js",
  "/_app/immutable/chunks/19-ce639c0a.js",
  "/_app/immutable/chunks/E-Mail_Change-e016f509.js",
  "/_app/immutable/chunks/NameChange-ec5e0afd.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-1b5b5a75.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1664730119959";
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
async function f() {
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(f());
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
