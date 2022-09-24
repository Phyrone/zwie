const o = [
  "/_app/immutable/assets/fa-solid-900-d27bc752.woff2",
  "/_app/immutable/assets/fa-solid-900-6d53c706.ttf",
  "/_app/immutable/start-f5dc90e0.js",
  "/_app/immutable/components/pages/_layout.svelte-5ee50d1d.js",
  "/_app/immutable/assets/_layout-83cd6551.css",
  "/_app/immutable/components/error.svelte-1693c51c.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-147c5ff4.js",
  "/_app/immutable/components/pages/(main)/Modus/_layout.svelte-f0ec9228.js",
  "/_app/immutable/components/pages/(main)/Plugins/_layout.svelte-ca61074b.js",
  "/_app/immutable/components/pages/(main)/Settings/_layout.svelte-160882db.js",
  "/_app/immutable/components/pages/(main)/Toolbox/_layout.svelte-b1f1ac48.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_layout.svelte-a2be4ce9.js",
  "/_app/immutable/components/pages/settings/_layout.svelte-6121230e.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-afdc6330.js",
  "/_app/immutable/components/pages/(main)/Modus/_page.svelte-98976bd4.js",
  "/_app/immutable/components/pages/(main)/Plugins/_page.svelte-8fd01ded.js",
  "/_app/immutable/components/pages/(main)/Settings/_page.svelte-2c1d7065.js",
  "/_app/immutable/components/pages/(main)/Settings/Audio/_page.svelte-d73b61f4.js",
  "/_app/immutable/components/pages/(main)/Settings/Language/_page.svelte-71084ed5.js",
  "/_app/immutable/components/pages/(main)/Settings/Overlay/_page.svelte-90043de7.js",
  "/_app/immutable/components/pages/(main)/Settings/Video/_page.svelte-b801464a.js",
  "/_app/immutable/components/pages/(main)/Toolbox/_page.svelte-a9a7f0d6.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-cceeee7a.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-db51f798.js",
  "/_app/immutable/components/pages/(main)/s/_server_/_page.svelte-3fdfc2ab.js",
  "/_app/immutable/components/pages/(main)/s/_server_/channel/_channel_/_page.svelte-ab44149d.js",
  "/_app/immutable/components/pages/settings/_page.svelte-e3ffc401.js",
  "/_app/immutable/modules/pages/_layout.ts-d347cb9b.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-a6b9ddf0.js",
  "/_app/immutable/chunks/singletons-9c97c4e4.js",
  "/_app/immutable/chunks/preload-helper-aa6bc0ce.js",
  "/_app/immutable/chunks/index-c260c7ce.js",
  "/_app/immutable/chunks/index-49b214d9.js",
  "/_app/immutable/chunks/messages-0552e578.js",
  "/_app/immutable/chunks/sweetalert2.all-30069934.js",
  "/_app/immutable/chunks/_layout-d02ecff8.js",
  "/_app/immutable/chunks/_page-9468531c.js",
  "/_app/immutable/chunks/0-e631bbe6.js",
  "/_app/immutable/chunks/1-8c757aba.js",
  "/_app/immutable/chunks/2-d7ba0f71.js",
  "/_app/immutable/chunks/3-de8c366d.js",
  "/_app/immutable/chunks/4-833e7532.js",
  "/_app/immutable/chunks/5-68b389de.js",
  "/_app/immutable/chunks/6-7a8361f3.js",
  "/_app/immutable/chunks/7-d295d38c.js",
  "/_app/immutable/chunks/8-db7deecd.js",
  "/_app/immutable/chunks/9-cf40a249.js",
  "/_app/immutable/chunks/10-95cf6496.js",
  "/_app/immutable/chunks/11-b7c11488.js",
  "/_app/immutable/chunks/12-6f074842.js",
  "/_app/immutable/chunks/13-d312e38f.js",
  "/_app/immutable/chunks/14-2d7ed10c.js",
  "/_app/immutable/chunks/15-db2daa6f.js",
  "/_app/immutable/chunks/16-bd831b1c.js",
  "/_app/immutable/chunks/17-c10b42f6.js",
  "/_app/immutable/chunks/18-abb508cb.js",
  "/_app/immutable/chunks/19-168e522a.js",
  "/_app/immutable/chunks/20-2dfe70e7.js",
  "/_app/immutable/chunks/21-6072c08c.js",
  "/_app/immutable/chunks/22-6ddf4aac.js",
  "/_app/immutable/chunks/23-f9790fec.js",
  "/_app/immutable/chunks/E-Mail_Change-6e886ce8.js",
  "/_app/immutable/chunks/NameChange-3c69b932.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-090ad614.js"
], u = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], _ = [
  "/"
], n = "1664048834223";
console.log("[ServiceWorker] Init...");
const b = [...o, ..._, ...u], m = "offline::", c = m + n;
let i = caches.open(c);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function d() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(m) && a !== c)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function r() {
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(r());
});
addEventListener("activate", (e) => {
  console.log("[ServiceWorker] Activate Event..."), e.waitUntil(d());
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
