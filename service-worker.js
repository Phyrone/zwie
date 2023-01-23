const l = [
  "/_app/immutable/assets/fa-solid-900-8f06540f.woff2",
  "/_app/immutable/assets/fa-solid-900-e4f6a7e9.ttf",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/assets/_layout-cf11c4b8.css",
  "/_app/immutable/chunks/messages-ecd8510d.js",
  "/_app/immutable/chunks/singletons-bb9e3252.js",
  "/_app/immutable/start-7a02228c.js",
  "/_app/immutable/chunks/index-0c7a8807.js",
  "/_app/immutable/chunks/theme-selector-93e4039c.js",
  "/_app/immutable/chunks/0-83d761e2.js",
  "/_app/immutable/chunks/1-3c87e2c2.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-0d31cf9e.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-f9044c4d.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-d693a48f.js",
  "/_app/immutable/chunks/preload-helper-ff6eb6cb.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-44b2ca75.js",
  "/_app/immutable/chunks/10-067026db.js",
  "/_app/immutable/chunks/11-307680ae.js",
  "/_app/immutable/chunks/2-1393b31d.js",
  "/_app/immutable/chunks/3-9d2e2df7.js",
  "/_app/immutable/chunks/4-62a7dc3e.js",
  "/_app/immutable/chunks/5-bceb27d7.js",
  "/_app/immutable/chunks/6-653e8f0d.js",
  "/_app/immutable/chunks/7-06c85e04.js",
  "/_app/immutable/chunks/8-6ed74041.js",
  "/_app/immutable/chunks/9-c3bc0822.js",
  "/_app/immutable/chunks/12-567fe066.js",
  "/_app/immutable/chunks/13-608cb110.js",
  "/_app/immutable/chunks/14-5decf599.js",
  "/_app/immutable/chunks/15-5046990b.js",
  "/_app/immutable/chunks/16-d25917ea.js",
  "/_app/immutable/chunks/17-bd7ce7ea.js",
  "/_app/immutable/chunks/18-747f5bfc.js",
  "/_app/immutable/chunks/19-3ed5374a.js",
  "/_app/immutable/modules/pages/_layout.ts-ee6743a5.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-94291217.js",
  "/_app/immutable/chunks/_layout-b00c1660.js",
  "/_app/immutable/components/error.svelte-56b3f84a.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-575cff42.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-dc5ac2f1.js",
  "/_app/immutable/chunks/_page-da46b06b.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-be06b136.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-fc406397.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-6b045904.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-58db60e1.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-b1cacfca.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-fc406397.js",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-3e967063.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-634afa17.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-44a521ad.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-fc406397.js",
  "/_app/immutable/chunks/E-Mail_Change-630025f8.js",
  "/_app/immutable/chunks/NameChange-7f024b4d.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-fe7239a8.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-b0d332c4.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-cdf1e1a8.js",
  "/_app/immutable/chunks/index-12acb24b.js",
  "/_app/immutable/chunks/sweetalert2.all-21fb228e.js",
  "/_app/immutable/components/pages/_layout.svelte-7a0a630c.js"
], o = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], u = [
  "/"
], p = "1674497668797";
console.log("[ServiceWorker] Init...");
const _ = [...l, ...u, ...o], n = "offline::", c = n + p;
let m = caches.open(c);
const i = self.location.host;
console.debug("[ServiceWorker] host=" + i);
async function r() {
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
    e = await Promise.all(_.map((s) => {
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
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(r());
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
