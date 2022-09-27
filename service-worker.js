const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-294dfbea.js",
  "/_app/immutable/components/pages/_layout.svelte-288baa76.js",
  "/_app/immutable/assets/_layout-9ff9c8f1.css",
  "/_app/immutable/components/error.svelte-9bbf465b.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-fc44aa74.js",
  "/_app/immutable/components/pages/(main)/Modus/_layout.svelte-f0ec9228.js",
  "/_app/immutable/components/pages/(main)/Plugins/_layout.svelte-ca61074b.js",
  "/_app/immutable/components/pages/(main)/Settings/_layout.svelte-adf6b330.js",
  "/_app/immutable/components/pages/(main)/Toolbox/_layout.svelte-b3152bbd.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-0381627e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-6e60f75c.js",
  "/_app/immutable/components/pages/(main)/Modus/_page.svelte-98976bd4.js",
  "/_app/immutable/components/pages/(main)/Plugins/_page.svelte-8fd01ded.js",
  "/_app/immutable/components/pages/(main)/Settings/_page.svelte-2c1d7065.js",
  "/_app/immutable/components/pages/(main)/Settings/audio/_page.svelte-1cc979c0.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/Settings/language/_page.svelte-b663597f.js",
  "/_app/immutable/components/pages/(main)/Settings/overlay/_page.svelte-78079d0b.js",
  "/_app/immutable/components/pages/(main)/Settings/video/_page.svelte-fd483e58.js",
  "/_app/immutable/components/pages/(main)/Toolbox/_page.svelte-a9a7f0d6.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-4b47a41b.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-57dd8361.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-4e9b2b2b.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-bb603cec.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/index-b31b03a2.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-93f16b68.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-16ef5bb2.js",
  "/_app/immutable/chunks/1-3335a1d9.js",
  "/_app/immutable/chunks/2-c9614fd9.js",
  "/_app/immutable/chunks/3-de8c366d.js",
  "/_app/immutable/chunks/4-833e7532.js",
  "/_app/immutable/chunks/5-b604bf3d.js",
  "/_app/immutable/chunks/6-145dd096.js",
  "/_app/immutable/chunks/7-6aaa1c2e.js",
  "/_app/immutable/chunks/8-582f5e49.js",
  "/_app/immutable/chunks/9-48585d74.js",
  "/_app/immutable/chunks/10-7d8c2c5d.js",
  "/_app/immutable/chunks/11-706f8869.js",
  "/_app/immutable/chunks/12-e7d0b7db.js",
  "/_app/immutable/chunks/13-8efb2109.js",
  "/_app/immutable/chunks/14-209b8e8d.js",
  "/_app/immutable/chunks/15-c201d77d.js",
  "/_app/immutable/chunks/16-6520ae40.js",
  "/_app/immutable/chunks/17-0f4239ae.js",
  "/_app/immutable/chunks/18-4431bd0f.js",
  "/_app/immutable/chunks/19-0da33530.js",
  "/_app/immutable/chunks/20-9f8ea15e.js",
  "/_app/immutable/chunks/21-4d49d384.js",
  "/_app/immutable/chunks/E-Mail_Change-6e886ce8.js",
  "/_app/immutable/chunks/NameChange-3c69b932.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1664263643350";
console.log("[ServiceWorker] Init...");
const b = [...o, ..._, ...u], m = "offline::", i = m + n;
let c = caches.open(i);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function r() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== i)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function d() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await c;
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
