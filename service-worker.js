const o = [
  "/_app/immutable/assets/fa-solid-900-8f06540f.woff2",
  "/_app/immutable/assets/fa-solid-900-e4f6a7e9.ttf",
  "/_app/immutable/start-dd56074e.js",
  "/_app/immutable/components/pages/_layout.svelte-b978ea04.js",
  "/_app/immutable/assets/_layout-af069a1b.css",
  "/_app/immutable/components/error.svelte-1940d221.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-a14cb4a9.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-bd4317c7.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-15ee1cc0.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-17aae81e.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-f9f22f52.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-ae189e8a.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-32b08d5e.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-77d05129.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-aa2fb4c1.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-be2b599a.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-ab470da7.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-ad45c885.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-cf12e923.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-050fcb1e.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-d265f05a.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-d62ab1ec.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-af53c119.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-c8f46089.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-a22d6ef8.js",
  "/_app/immutable/chunks/index-5a6d03c0.js",
  "/_app/immutable/chunks/index-c9e3a8b1.js",
  "/_app/immutable/chunks/preload-helper-b21cceae.js",
  "/_app/immutable/chunks/theme-selector-78bffc28.js",
  "/_app/immutable/chunks/messages-002a3112.js",
  "/_app/immutable/chunks/sweetalert2.all-e4c86e28.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-f7e6847d.js",
  "/_app/immutable/chunks/1-15524e66.js",
  "/_app/immutable/chunks/2-e21780bf.js",
  "/_app/immutable/chunks/3-7e240590.js",
  "/_app/immutable/chunks/4-681091c8.js",
  "/_app/immutable/chunks/5-8acdf6f3.js",
  "/_app/immutable/chunks/6-25288720.js",
  "/_app/immutable/chunks/7-0fbede7d.js",
  "/_app/immutable/chunks/8-11917eab.js",
  "/_app/immutable/chunks/9-6dbe384e.js",
  "/_app/immutable/chunks/10-68d08d9e.js",
  "/_app/immutable/chunks/11-3ed0c39d.js",
  "/_app/immutable/chunks/12-c49f11e8.js",
  "/_app/immutable/chunks/13-00f734de.js",
  "/_app/immutable/chunks/14-ae80cb35.js",
  "/_app/immutable/chunks/15-35451d8c.js",
  "/_app/immutable/chunks/16-f49d0332.js",
  "/_app/immutable/chunks/17-c85d8f89.js",
  "/_app/immutable/chunks/18-e03c5f8f.js",
  "/_app/immutable/chunks/19-85f90867.js",
  "/_app/immutable/chunks/E-Mail_Change-6e753372.js",
  "/_app/immutable/chunks/NameChange-51f99da3.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-ac29f8ba.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1669355661455";
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
async function d() {
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
async function f(e) {
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
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(b());
});
addEventListener(
  "fetch",
  async (e) => {
    new URL(e.request.url).host === l && e.respondWith(f(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
