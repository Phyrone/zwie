const c = [
  "/_app/immutable/assets/fa-solid-900-8f06540f.woff2",
  "/_app/immutable/assets/fa-solid-900-e4f6a7e9.ttf",
  "/_app/immutable/assets/_page-91244bf2.css",
  "/_app/immutable/assets/_layout-8506a42f.css",
  "/_app/immutable/chunks/messages-694a90e8.js",
  "/_app/immutable/chunks/_page-da46b06b.js",
  "/_app/immutable/chunks/preload-helper-ff6eb6cb.js",
  "/_app/immutable/components/pages/(main)/_layout.svelte-080b6403.js",
  "/_app/immutable/start-6f43c4cb.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_layout.svelte-e58e1bcf.js",
  "/_app/immutable/components/pages/(main)/settings/_layout.svelte-694fb5cb.js",
  "/_app/immutable/components/pages/(main)/toolbox/_layout.svelte-99dd67e8.js",
  "/_app/immutable/components/pages/(main)/_page.svelte-e38a00c7.js",
  "/_app/immutable/components/pages/(main)/plugins/_layout.svelte-594a5ce9.js",
  "/_app/immutable/chunks/index-d77303d9.js",
  "/_app/immutable/chunks/index-0a7410a1.js",
  "/_app/immutable/chunks/singletons-ebd4c455.js",
  "/_app/immutable/chunks/theme-selector-fa5aab73.js",
  "/_app/immutable/chunks/5-957af406.js",
  "/_app/immutable/chunks/11-bdea5d0e.js",
  "/_app/immutable/chunks/12-22f1027d.js",
  "/_app/immutable/chunks/13-039255ef.js",
  "/_app/immutable/chunks/14-67ed8bd7.js",
  "/_app/immutable/chunks/15-8226d812.js",
  "/_app/immutable/chunks/16-658c6b41.js",
  "/_app/immutable/chunks/17-3ad9a109.js",
  "/_app/immutable/chunks/18-781c6352.js",
  "/_app/immutable/chunks/19-c019b7f9.js",
  "/_app/immutable/chunks/6-daf654f0.js",
  "/_app/immutable/chunks/7-b99ff721.js",
  "/_app/immutable/chunks/8-d1bf1d11.js",
  "/_app/immutable/chunks/9-797c981d.js",
  "/_app/immutable/chunks/10-f7b8989c.js",
  "/_app/immutable/chunks/NameChange-899c93fe.js",
  "/_app/immutable/modules/pages/_layout.ts-ee6743a5.js",
  "/_app/immutable/modules/pages/(main)/_page.ts-94291217.js",
  "/_app/immutable/chunks/E-Mail_Change-7fffebfa.js",
  "/_app/immutable/chunks/_layout-b00c1660.js",
  "/_app/immutable/chunks/4-1243daed.js",
  "/_app/immutable/components/pages/(main)/settings/audio/_page.svelte-def8a843.js",
  "/_app/immutable/chunks/0-ada6da86.js",
  "/_app/immutable/chunks/1-7c6bd4b1.js",
  "/_app/immutable/chunks/2-9265aa9f.js",
  "/_app/immutable/components/pages/(main)/dm/_user_/_page.svelte-fde8501d.js",
  "/_app/immutable/components/pages/(main)/plugins/_page.svelte-bb173f19.js",
  "/_app/immutable/components/pages/(main)/server/_server_/_page.svelte-6937d300.js",
  "/_app/immutable/components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-58477e1d.js",
  "/_app/immutable/components/pages/(main)/settings/_page.svelte-bb173f19.js",
  "/_app/immutable/chunks/AvatarSelectPopUp-293d32a6.js",
  "/_app/immutable/components/pages/(main)/toolbox/_page.svelte-bb173f19.js",
  "/_app/immutable/components/pages/(main)/profile/_page.svelte-c6c90c3d.js",
  "/_app/immutable/components/pages/(main)/settings/language/_page.svelte-c03b353c.js",
  "/_app/immutable/chunks/3-1d73f270.js",
  "/_app/immutable/components/pages/(main)/settings/overlay/_page.svelte-c8e2aa0c.js",
  "/_app/immutable/components/pages/(main)/settings/video/_page.svelte-6d08f0bd.js",
  "/_app/immutable/components/error.svelte-865301cd.js",
  "/_app/immutable/components/pages/(main)/dm/_page.svelte-11dae875.js",
  "/_app/immutable/chunks/sweetalert2.all-a459e879.js",
  "/_app/immutable/components/pages/_layout.svelte-63be8e7b.js"
], o = [
  "/app-icon-dev-rounded.png",
  "/favicon.ico",
  "/manifest.webmanifest"
], u = [
  "/"
], p = "1671561000297";
console.log("[ServiceWorker] Init...");
const _ = [...c, ...u, ...o], n = "offline::", m = n + p;
let i = caches.open(m);
const l = self.location.host;
console.debug("[ServiceWorker] host=" + l);
async function b() {
  try {
    console.time("[ServiceWorker] deleting old caches"), await Promise.all(await caches.keys().then((e) => e.filter((a) => a.startsWith(n) && a !== m)).then((e) => e.map((a) => caches.delete(a))));
  } finally {
    console.timeEnd("[ServiceWorker] deleting old caches");
  }
}
async function r() {
  console.time(`[ServiceWorker] Creating/Updating Cache... (${p})`);
  let e = -1;
  try {
    let a = await i;
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
async function d(e) {
  let a = await i, s = await a.match(e);
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
  console.log("[ServiceWorker] Install Event..."), e.waitUntil(r());
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
