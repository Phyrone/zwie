const o = [
  "/_app/immutable/assets/fa-solid-900-8f06540f.woff2",
  "/_app/immutable/assets/fa-solid-900-e4f6a7e9.ttf",
  "/_app/immutable/start-c2f86ab7.js",
  "/_app/immutable/components/pages/_layout.svelte-a87a8892.js",
  "/_app/immutable/assets/_layout-af069a1b.css",
  "/_app/immutable/components/error.svelte-a44363fa.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-2695af3e.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-f8bbe0d4.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-d7804287.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-87f58d90.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-2ae82694.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-1e7eb05a.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-b169d66e.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-0f51ce97.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-f7370119.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-ea9dcdf8.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-367be3b1.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-c01b886f.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-b5625e41.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-2b0ec8c3.js",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-b5ccfa2b.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-86f6b349.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-6a6d0789.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-08da42c7.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-0bbcedc4.js",
  "/_app/immutable/chunks/index-bda01f89.js",
  "/_app/immutable/chunks/index-01e9ce15.js",
  "/_app/immutable/chunks/preload-helper-b21cceae.js",
  "/_app/immutable/chunks/theme-selector-08006b59.js",
  "/_app/immutable/chunks/messages-e29e5c88.js",
  "/_app/immutable/chunks/sweetalert2.all-6ad8320b.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-eeea3f79.js",
  "/_app/immutable/chunks/1-bbd5e65b.js",
  "/_app/immutable/chunks/2-fcb2da99.js",
  "/_app/immutable/chunks/3-69a2898a.js",
  "/_app/immutable/chunks/4-063dbffb.js",
  "/_app/immutable/chunks/5-c4203239.js",
  "/_app/immutable/chunks/6-7ebf2d84.js",
  "/_app/immutable/chunks/7-b87e3a16.js",
  "/_app/immutable/chunks/8-91372596.js",
  "/_app/immutable/chunks/9-568ee5e1.js",
  "/_app/immutable/chunks/10-6c63baf1.js",
  "/_app/immutable/chunks/11-06c584f4.js",
  "/_app/immutable/chunks/12-4656b9ec.js",
  "/_app/immutable/chunks/13-f6892528.js",
  "/_app/immutable/chunks/14-6e3b45d7.js",
  "/_app/immutable/chunks/15-fc80d2af.js",
  "/_app/immutable/chunks/16-567fb683.js",
  "/_app/immutable/chunks/17-9a2932e1.js",
  "/_app/immutable/chunks/18-9071b183.js",
  "/_app/immutable/chunks/19-a367ea35.js",
  "/_app/immutable/chunks/E-Mail_Change-54da0ab8.js",
  "/_app/immutable/chunks/NameChange-ff7bfb3e.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-2367c311.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1670484628262";
console.log("[ServiceWorker] Init...");
const b = [...o, ..._, ...u], m = "offline::", i = m + n;
let l = caches.open(i);
const c = self.location.host;
console.debug("[ServiceWorker] host=" + c);
async function r() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== i)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function f() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${n})`);
  let e = -1;
  try {
    let a = await l;
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
  let a = await l, s = await a.match(e);
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
    new URL(e.request.url).host === c && e.respondWith(h(e.request));
  }
);
self.addEventListener("message", (e) => {
  e.data === "APPLY_UPDATE" && self.skipWaiting();
});
