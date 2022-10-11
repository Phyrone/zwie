import{_ as S}from"./chunks/preload-helper-aa6bc0ce.js";import{S as We,i as Xe,s as Ye,a as Qe,e as T,c as Ze,b as K,g as G,t as D,d as M,f as j,h as q,j as xe,o as $e,k as et,l as tt,m as rt,n as be,p as W,q as nt,r as at,u as it,v as C,w as B,x as te,y as J,z,A as Ce}from"./chunks/index-46970648.js";import{g as Be,f as Je,s as ee,a as Ee,b as ot,i as st}from"./chunks/singletons-72a8a4bd.js";class ye{constructor(e,r){this.status=e,typeof r=="string"?this.body={message:r}:r?this.body=r:this.body={message:`Error: ${e}`}}toString(){return JSON.stringify(this.body)}}class ze{constructor(e,r){this.status=e,this.location=r}}function lt(n,e){return n==="/"||e==="ignore"?n:e==="never"?n.endsWith("/")?n.slice(0,-1):n:e==="always"&&!n.endsWith("/")?n+"/":n}function ct(n){for(const e in n)n[e]=n[e].replace(/%23/g,"#").replace(/%3[Bb]/g,";").replace(/%2[Cc]/g,",").replace(/%2[Ff]/g,"/").replace(/%3[Ff]/g,"?").replace(/%3[Aa]/g,":").replace(/%40/g,"@").replace(/%26/g,"&").replace(/%3[Dd]/g,"=").replace(/%2[Bb]/g,"+").replace(/%24/g,"$");return n}const ft=["href","pathname","search","searchParams","toString","toJSON"];function ut(n,e){const r=new URL(n);for(const i of ft){let a=r[i];Object.defineProperty(r,i,{get(){return e(),a},enumerable:!0,configurable:!0})}return r[Symbol.for("nodejs.util.inspect.custom")]=(i,a,u)=>u(n,a),_t(r),r}function _t(n){Object.defineProperty(n,"hash",{get(){throw new Error("Cannot access event.url.hash. Consider using `$page.url.hash` inside a component instead")}})}function pt(n){let e=5381,r=n.length;if(typeof n=="string")for(;r;)e=e*33^n.charCodeAt(--r);else for(;r;)e=e*33^n[--r];return(e>>>0).toString(36)}const Le=window.fetch;window.fetch=(n,e)=>{if((n instanceof Request?n.method:(e==null?void 0:e.method)||"GET")!=="GET"){const i=new URL(n instanceof Request?n.url:n.toString(),document.baseURI).href;fe.delete(i)}return Le(n,e)};const fe=new Map;function dt(n,e,r){let a=`script[data-sveltekit-fetched][data-url=${JSON.stringify(typeof n=="string"?n:n.url)}]`;r&&typeof r.body=="string"&&(a+=`[data-hash="${pt(r.body)}"]`);const u=document.querySelector(a);if(u!=null&&u.textContent){const{body:t,...s}=JSON.parse(u.textContent),l=u.getAttribute("data-ttl");return l&&fe.set(e,{body:t,init:s,ttl:1e3*Number(l)}),Promise.resolve(new Response(t,s))}return Le(n,r)}function mt(n,e){const r=fe.get(n);if(r){if(performance.now()<r.ttl)return new Response(r.body,r.init);fe.delete(n)}return Le(n,e)}const ht=/^(\.\.\.)?(\w+)(?:=(\w+))?$/;function gt(n){const e=[],r=[];let i=!0;return{pattern:n===""?/^\/$/:new RegExp(`^${n.split(/(?:\/|$)/).filter(wt).map((u,t,s)=>{const l=decodeURIComponent(u),_=/^\[\.\.\.(\w+)(?:=(\w+))?\]$/.exec(l);if(_)return e.push(_[1]),r.push(_[2]),"(?:/(.*))?";const w=t===s.length-1;return l&&"/"+l.split(/\[(.+?)\]/).map((R,N)=>{if(N%2){const F=ht.exec(R);if(!F)throw new Error(`Invalid param: ${R}. Params and matcher names can only have underscores and alphanumeric characters.`);const[,y,H,X]=F;return e.push(H),r.push(X),y?"(.*?)":"([^/]+?)"}return w&&R.includes(".")&&(i=!1),R.normalize().replace(/%5[Bb]/g,"[").replace(/%5[Dd]/g,"]").replace(/#/g,"%23").replace(/\?/g,"%3F").replace(/[.*+?^${}()|[\]\\]/g,"\\$&")}).join("")}).join("")}${i?"/?":""}$`),names:e,types:r}}function wt(n){return!/^\([^)]+\)$/.test(n)}function bt(n,e,r,i){const a={};for(let u=0;u<e.length;u+=1){const t=e[u],s=r[u],l=n[u+1]||"";if(s){const _=i[s];if(!_)throw new Error(`Missing "${s}" param matcher`);if(!_(l))return}a[t]=l}return a}function yt(n,e,r,i){const a=new Set(e);return Object.entries(r).map(([s,[l,_,w]])=>{const{pattern:R,names:N,types:F}=gt(s),y={id:s,exec:H=>{const X=R.exec(H);if(X)return bt(X,N,F,i)},errors:[1,...w||[]].map(H=>n[H]),layouts:[0,..._||[]].map(t),leaf:u(l)};return y.errors.length=y.layouts.length=Math.max(y.errors.length,y.layouts.length),y});function u(s){const l=s<0;return l&&(s=~s),[l,n[s]]}function t(s){return s===void 0?s:[a.has(s),n[s]]}}function vt(n){let e,r,i;var a=n[0][0];function u(t){return{props:{data:t[2],form:t[1]}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&4&&(l.data=t[2]),s&2&&(l.form=t[1]),a!==(a=t[0][0])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function $t(n){let e,r,i;var a=n[0][0];function u(t){return{props:{data:t[2],$$slots:{default:[It]},$$scope:{ctx:t}}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&4&&(l.data=t[2]),s&2107&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][0])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function Et(n){let e,r,i;var a=n[0][1];function u(t){return{props:{data:t[3],form:t[1]}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&8&&(l.data=t[3]),s&2&&(l.form=t[1]),a!==(a=t[0][1])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function kt(n){let e,r,i;var a=n[0][1];function u(t){return{props:{data:t[3],$$slots:{default:[Pt]},$$scope:{ctx:t}}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&8&&(l.data=t[3]),s&2099&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][1])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function Rt(n){let e,r,i;var a=n[0][2];function u(t){return{props:{data:t[4],form:t[1]}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&16&&(l.data=t[4]),s&2&&(l.form=t[1]),a!==(a=t[0][2])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function Lt(n){let e,r,i;var a=n[0][2];function u(t){return{props:{data:t[4],$$slots:{default:[Ot]},$$scope:{ctx:t}}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&16&&(l.data=t[4]),s&2083&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][2])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function Ot(n){let e,r,i;var a=n[0][3];function u(t){return{props:{data:t[5],form:t[1]}}}return a&&(e=C(a,u(n))),{c(){e&&B(e.$$.fragment),r=T()},l(t){e&&te(e.$$.fragment,t),r=T()},m(t,s){e&&J(e,t,s),K(t,r,s),i=!0},p(t,s){const l={};if(s&32&&(l.data=t[5]),s&2&&(l.form=t[1]),a!==(a=t[0][3])){if(e){G();const _=e;D(_.$$.fragment,1,0,()=>{z(_,1)}),M()}a?(e=C(a,u(t)),B(e.$$.fragment),j(e.$$.fragment,1),J(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&j(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&z(e,t)}}}function Pt(n){let e,r,i,a;const u=[Lt,Rt],t=[];function s(l,_){return l[0][3]?0:1}return e=s(n),r=t[e]=u[e](n),{c(){r.c(),i=T()},l(l){r.l(l),i=T()},m(l,_){t[e].m(l,_),K(l,i,_),a=!0},p(l,_){let w=e;e=s(l),e===w?t[e].p(l,_):(G(),D(t[w],1,1,()=>{t[w]=null}),M(),r=t[e],r?r.p(l,_):(r=t[e]=u[e](l),r.c()),j(r,1),r.m(i.parentNode,i))},i(l){a||(j(r),a=!0)},o(l){D(r),a=!1},d(l){t[e].d(l),l&&q(i)}}}function It(n){let e,r,i,a;const u=[kt,Et],t=[];function s(l,_){return l[0][2]?0:1}return e=s(n),r=t[e]=u[e](n),{c(){r.c(),i=T()},l(l){r.l(l),i=T()},m(l,_){t[e].m(l,_),K(l,i,_),a=!0},p(l,_){let w=e;e=s(l),e===w?t[e].p(l,_):(G(),D(t[w],1,1,()=>{t[w]=null}),M(),r=t[e],r?r.p(l,_):(r=t[e]=u[e](l),r.c()),j(r,1),r.m(i.parentNode,i))},i(l){a||(j(r),a=!0)},o(l){D(r),a=!1},d(l){t[e].d(l),l&&q(i)}}}function Ke(n){let e,r=n[7]&&Fe(n);return{c(){e=et("div"),r&&r.c(),this.h()},l(i){e=tt(i,"DIV",{id:!0,"aria-live":!0,"aria-atomic":!0,style:!0});var a=rt(e);r&&r.l(a),a.forEach(q),this.h()},h(){be(e,"id","svelte-announcer"),be(e,"aria-live","assertive"),be(e,"aria-atomic","true"),W(e,"position","absolute"),W(e,"left","0"),W(e,"top","0"),W(e,"clip","rect(0 0 0 0)"),W(e,"clip-path","inset(50%)"),W(e,"overflow","hidden"),W(e,"white-space","nowrap"),W(e,"width","1px"),W(e,"height","1px")},m(i,a){K(i,e,a),r&&r.m(e,null)},p(i,a){i[7]?r?r.p(i,a):(r=Fe(i),r.c(),r.m(e,null)):r&&(r.d(1),r=null)},d(i){i&&q(e),r&&r.d()}}}function Fe(n){let e;return{c(){e=nt(n[8])},l(r){e=at(r,n[8])},m(r,i){K(r,e,i)},p(r,i){i&256&&it(e,r[8])},d(r){r&&q(e)}}}function St(n){let e,r,i,a,u;const t=[$t,vt],s=[];function l(w,R){return w[0][1]?0:1}e=l(n),r=s[e]=t[e](n);let _=n[6]&&Ke(n);return{c(){r.c(),i=Qe(),_&&_.c(),a=T()},l(w){r.l(w),i=Ze(w),_&&_.l(w),a=T()},m(w,R){s[e].m(w,R),K(w,i,R),_&&_.m(w,R),K(w,a,R),u=!0},p(w,[R]){let N=e;e=l(w),e===N?s[e].p(w,R):(G(),D(s[N],1,1,()=>{s[N]=null}),M(),r=s[e],r?r.p(w,R):(r=s[e]=t[e](w),r.c()),j(r,1),r.m(i.parentNode,i)),w[6]?_?_.p(w,R):(_=Ke(w),_.c(),_.m(a.parentNode,a)):_&&(_.d(1),_=null)},i(w){u||(j(r),u=!0)},o(w){D(r),u=!1},d(w){s[e].d(w),w&&q(i),_&&_.d(w),w&&q(a)}}}function At(n,e,r){let{stores:i}=e,{page:a}=e,{components:u}=e,{form:t}=e,{data_0:s=null}=e,{data_1:l=null}=e,{data_2:_=null}=e,{data_3:w=null}=e;xe(i.page.notify);let R=!1,N=!1,F=null;return $e(()=>{const y=i.page.subscribe(()=>{R&&(r(7,N=!0),r(8,F=document.title||"untitled page"))});return r(6,R=!0),y}),n.$$set=y=>{"stores"in y&&r(9,i=y.stores),"page"in y&&r(10,a=y.page),"components"in y&&r(0,u=y.components),"form"in y&&r(1,t=y.form),"data_0"in y&&r(2,s=y.data_0),"data_1"in y&&r(3,l=y.data_1),"data_2"in y&&r(4,_=y.data_2),"data_3"in y&&r(5,w=y.data_3)},n.$$.update=()=>{n.$$.dirty&1536&&i.page.set(a)},[u,t,s,l,_,w,R,N,F,i,a]}class Tt extends We{constructor(e){super(),Xe(this,e,At,St,Ye,{stores:9,page:10,components:0,form:1,data_0:2,data_1:3,data_2:4,data_3:5})}}const Dt={},ue=[()=>S(()=>import("./chunks/0-76dec20f.js"),["chunks/0-76dec20f.js","chunks/_layout-d02ecff8.js","components/pages/_layout.svelte-9383bfca.js","assets/_layout-e002bbc2.css","chunks/index-46970648.js","chunks/messages-9145f738.js","chunks/index-bc01506a.js","chunks/sweetalert2.all-7f7190b5.js","chunks/theme-selector-7a652417.js"],import.meta.url),()=>S(()=>import("./chunks/1-1601f0bc.js"),["chunks/1-1601f0bc.js","components/error.svelte-f39a5ad0.js","chunks/index-46970648.js","chunks/singletons-72a8a4bd.js","chunks/index-bc01506a.js"],import.meta.url),()=>S(()=>import("./chunks/2-cf493d06.js"),["chunks/2-cf493d06.js","components/pages/(main)/_layout.svelte-2fc3f808.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/3-b94c73ba.js"),["chunks/3-b94c73ba.js","components/pages/(main)/plugins/_layout.svelte-eff5e340.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/4-22383c7b.js"),["chunks/4-22383c7b.js","components/pages/(main)/server/_server_/_layout.svelte-a309595c.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/5-ca942d3e.js"),["chunks/5-ca942d3e.js","components/pages/(main)/settings/_layout.svelte-6558d2fb.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/6-1be27d27.js"),["chunks/6-1be27d27.js","components/pages/(main)/toolbox/_layout.svelte-700ca416.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/7-4734dcc3.js"),["chunks/7-4734dcc3.js","chunks/_page-9468531c.js","components/pages/(main)/_page.svelte-8375cefd.js","chunks/index-46970648.js","chunks/sweetalert2.all-7f7190b5.js","chunks/messages-9145f738.js","chunks/index-bc01506a.js"],import.meta.url),()=>S(()=>import("./chunks/8-9b54694d.js"),["chunks/8-9b54694d.js","components/pages/(main)/dm/_page.svelte-3b33fd40.js","chunks/preload-helper-aa6bc0ce.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/9-85fe252a.js"),["chunks/9-85fe252a.js","components/pages/(main)/dm/_user_/_page.svelte-4f9420f7.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/10-c95fb5b9.js"),["chunks/10-c95fb5b9.js","components/pages/(main)/plugins/_page.svelte-263a3281.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/11-6cf27401.js"),["chunks/11-6cf27401.js","components/pages/(main)/profile/_page.svelte-69222be4.js","chunks/preload-helper-aa6bc0ce.js","chunks/index-46970648.js","chunks/sweetalert2.all-7f7190b5.js"],import.meta.url),()=>S(()=>import("./chunks/12-16657f50.js"),["chunks/12-16657f50.js","components/pages/(main)/server/_server_/_page.svelte-19617a80.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/13-316b8575.js"),["chunks/13-316b8575.js","components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-a0501fc6.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/14-09c45082.js"),["chunks/14-09c45082.js","components/pages/(main)/settings/_page.svelte-a1d44145.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/15-ff452ff9.js"),["chunks/15-ff452ff9.js","components/pages/(main)/settings/audio/_page.svelte-5d29e7e9.js","assets/_page-91244bf2.css","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/16-4ce78513.js"),["chunks/16-4ce78513.js","components/pages/(main)/settings/language/_page.svelte-687d315e.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/17-22b7aa83.js"),["chunks/17-22b7aa83.js","components/pages/(main)/settings/overlay/_page.svelte-08a271cc.js","chunks/index-46970648.js","chunks/theme-selector-7a652417.js","chunks/index-bc01506a.js"],import.meta.url),()=>S(()=>import("./chunks/18-80f0f14d.js"),["chunks/18-80f0f14d.js","components/pages/(main)/settings/video/_page.svelte-286d36b2.js","chunks/index-46970648.js"],import.meta.url),()=>S(()=>import("./chunks/19-a036449f.js"),["chunks/19-a036449f.js","components/pages/(main)/toolbox/_page.svelte-fb1b2a41.js","chunks/index-46970648.js"],import.meta.url)],jt=[],Vt={"(main)":[7,[2]],"(main)/dm":[8,[2]],"(main)/plugins":[10,[2,3]],"(main)/profile":[11,[2]],"(main)/settings":[14,[2,5]],"(main)/toolbox":[19,[2,6]],"(main)/settings/audio":[15,[2,5]],"(main)/settings/language":[16,[2,5]],"(main)/settings/overlay":[17,[2,5]],"(main)/settings/video":[18,[2,5]],"(main)/dm/[user]":[9,[2]],"(main)/server/[server]":[12,[2,4]],"(main)/server/[server]/channel/[channel]":[13,[2,4]]},Ut={handleError:({error:n})=>(console.error(n),{message:"Internal Error"})},Nt="/__data.js",He="sveltekit:scroll",Z="sveltekit:index",se=yt(ue,jt,Vt,Dt),ke=ue[0],Re=ue[1];ke();Re();let ie={};try{ie=JSON.parse(sessionStorage[He])}catch{}function ve(n){ie[n]=Ee()}function qt({target:n,base:e,trailing_slash:r}){var Ve;const i=[],a={id:null,promise:null},u={before_navigate:[],after_navigate:[]};let t={branch:[],error:null,session_id:0,url:null},s=!1,l=!0,_=!1,w=1,R=null,N=!1,F,y=(Ve=history.state)==null?void 0:Ve[Z];y||(y=Date.now(),history.replaceState({...history.state,[Z]:y},"",location.href));const H=ie[y];H&&(history.scrollRestoration="manual",scrollTo(H.x,H.y));let X=!1,Y,Oe;function Pe(){if(!R){const o=new URL(location.href);R=Promise.resolve().then(async()=>{const f=me(o,!0);await Se(f,o,[]),R=null,N=!1})}return R}async function _e(o,{noscroll:f=!1,replaceState:m=!1,keepfocus:c=!1,state:p={}},h){return typeof o=="string"&&(o=new URL(o,Be(document))),he({url:o,scroll:f?Ee():null,keepfocus:c,redirect_chain:h,details:{state:p,replaceState:m},accepted:()=>{},blocked:()=>{},type:"goto"})}async function Ie(o){const f=me(o,!1);if(!f)throw new Error("Attempted to prefetch a URL that does not belong to this app");return a.promise=De(f),a.id=f.id,a.promise}async function Se(o,f,m,c,p){var $,E;const h=Oe={};let g=o&&await De(o);if(!g&&f.origin===location.origin&&f.pathname===location.pathname&&(g=await oe({status:404,error:new Error(`Not found: ${f.pathname}`),url:f,routeId:null})),!g)return await ne(f),!1;if(f=(o==null?void 0:o.url)||f,Oe!==h)return!1;if(i.length=0,g.type==="redirect")if(m.length>10||m.includes(f.pathname))g=await oe({status:500,error:new Error("Redirect loop"),url:f,routeId:null});else return _e(new URL(g.location,f).href,{},[...m,f.pathname]),!1;else((E=($=g.props)==null?void 0:$.page)==null?void 0:E.status)>=400&&await ee.updated.check()&&await ne(f);if(_=!0,c&&c.details){const{details:v}=c,b=v.replaceState?0:1;v.state[Z]=y+=b,history[v.replaceState?"replaceState":"pushState"](v.state,"",f)}if(s){t=g.state,g.props.page&&(g.props.page.url=f);const v=ce();F.$set(g.props),v()}else Ae(g);if(c){const{scroll:v,keepfocus:b}=c;if(!b){const k=document.body,A=k.getAttribute("tabindex");k.tabIndex=-1,k.focus({preventScroll:!0}),setTimeout(()=>{var O;(O=getSelection())==null||O.removeAllRanges()}),A!==null?k.setAttribute("tabindex",A):k.removeAttribute("tabindex")}if(await Ce(),l){const k=f.hash&&document.getElementById(f.hash.slice(1));v?scrollTo(v.x,v.y):k?k.scrollIntoView():scrollTo(0,0)}}else await Ce();a.promise=null,a.id=null,l=!0,g.props.page&&(Y=g.props.page),p&&p(),_=!1}function Ae(o){var p,h;t=o.state;const f=document.querySelector("style[data-sveltekit]");f&&f.remove(),Y=o.props.page;const m=ce();F=new Tt({target:n,props:{...o.props,stores:ee},hydrate:!0}),m();const c={from:null,to:le("to",{params:t.params,routeId:(h=(p=t.route)==null?void 0:p.id)!=null?h:null,url:new URL(location.href)}),type:"load"};u.after_navigate.forEach(g=>g(c)),s=!0}async function re({url:o,params:f,branch:m,status:c,error:p,route:h,form:g}){var A;const $=m.filter(Boolean),E={type:"loaded",state:{url:o,params:f,branch:m,error:p,route:h,session_id:w},props:{components:$.map(O=>O.node.component)}};g!==void 0&&(E.props.form=g);let v={},b=!Y;for(let O=0;O<$.length;O+=1){const V=$[O];v={...v,...V.data},(b||!t.branch.some(U=>U===V))&&(E.props[`data_${O}`]=v,b=b||Object.keys((A=V.data)!=null?A:{}).length>0)}if(b||(b=Object.keys(Y.data).length!==Object.keys(v).length),!t.url||o.href!==t.url.href||t.error!==p||b){E.props.page={error:p,params:f,routeId:h&&h.id,status:c,url:o,data:b?v:Y.data};const O=(V,U)=>{Object.defineProperty(E.props.page,V,{get:()=>{throw new Error(`$page.${V} has been replaced by $page.url.${U}`)}})};O("origin","origin"),O("path","pathname"),O("query","searchParams")}return E}async function pe({loader:o,parent:f,url:m,params:c,routeId:p,server_data_node:h}){var v,b,k,A,O;let g=null;const $={dependencies:new Set,params:new Set,parent:!1,url:!1},E=await o();if((v=E.shared)!=null&&v.load){let V=function(...d){for(const L of d){const{href:P}=new URL(L,m);$.dependencies.add(P)}};const U={};for(const d in c)Object.defineProperty(U,d,{get(){return $.params.add(d),c[d]},enumerable:!0});const Q={routeId:p,params:U,data:(b=h==null?void 0:h.data)!=null?b:null,url:ut(m,()=>{$.url=!0}),async fetch(d,L){let P;typeof d=="string"?P=d:(P=d.url,L={body:d.method==="GET"||d.method==="HEAD"?void 0:await d.blob(),cache:d.cache,credentials:d.credentials,headers:d.headers,integrity:d.integrity,keepalive:d.keepalive,method:d.method,mode:d.mode,redirect:d.redirect,referrer:d.referrer,referrerPolicy:d.referrerPolicy,signal:d.signal,...L});const I=new URL(P,m).href;return V(I),s?mt(I,L):dt(P,I,L)},setHeaders:()=>{},depends:V,parent(){return $.parent=!0,f()}};Object.defineProperties(Q,{props:{get(){throw new Error("@migration task: Replace `props` with `data` stuff https://github.com/sveltejs/kit/discussions/5774#discussioncomment-3292693")},enumerable:!1},session:{get(){throw new Error("session is no longer available. See https://github.com/sveltejs/kit/discussions/5883")},enumerable:!1},stuff:{get(){throw new Error("@migration task: Remove stuff https://github.com/sveltejs/kit/discussions/5774#discussioncomment-3292693")},enumerable:!1}}),g=(k=await E.shared.load.call(null,Q))!=null?k:null}return{node:E,loader:o,server:h,shared:(A=E.shared)!=null&&A.load?{type:"data",data:g,uses:$}:null,data:(O=g!=null?g:h==null?void 0:h.data)!=null?O:null}}function Te(o,f,m){if(N)return!0;if(!m)return!1;if(m.parent&&f||o.url&&m.url)return!0;for(const c of o.params)if(m.params.has(c))return!0;for(const c of m.dependencies)if(i.some(p=>p(new URL(c))))return!0;return!1}function de(o,f){var m,c;return(o==null?void 0:o.type)==="data"?{type:"data",data:o.data,uses:{dependencies:new Set((m=o.uses.dependencies)!=null?m:[]),params:new Set((c=o.uses.params)!=null?c:[]),parent:!!o.uses.parent,url:!!o.uses.url}}:(o==null?void 0:o.type)==="skip"&&f!=null?f:null}async function De({id:o,invalidating:f,url:m,params:c,route:p}){var Q;if(a.id===o&&a.promise)return a.promise;const{errors:h,layouts:g,leaf:$}=p,E=t.url&&{url:o!==t.url.pathname+t.url.search,params:Object.keys(c).filter(d=>t.params[d]!==c[d])},v=[...g,$];h.forEach(d=>d==null?void 0:d().catch(()=>{})),v.forEach(d=>d==null?void 0:d[1]().catch(()=>{}));let b=null;const k=v.reduce((d,L,P)=>{var x;const I=t.branch[P],ae=!!(L!=null&&L[0])&&((I==null?void 0:I.loader)!==L[1]||Te(E,d.some(Boolean),(x=I.server)==null?void 0:x.uses));return d.push(ae),d},[]);if(k.some(Boolean)){try{b=await Ge(m,k)}catch(d){return oe({status:500,error:d,url:m,routeId:p.id})}if(b.type==="redirect")return b}const A=b==null?void 0:b.nodes;let O=!1;const V=v.map(async(d,L)=>{var ge,Ue;if(!d)return;const P=t.branch[L],I=(ge=A==null?void 0:A[L])!=null?ge:null;if((!I||I.type==="skip")&&d[1]===(P==null?void 0:P.loader)&&!Te(E,O,(Ue=P.shared)==null?void 0:Ue.uses))return P;if(O=!0,(I==null?void 0:I.type)==="error")throw I;return pe({loader:d[1],url:m,params:c,routeId:p.id,parent:async()=>{var qe;const Ne={};for(let we=0;we<L;we+=1)Object.assign(Ne,(qe=await V[we])==null?void 0:qe.data);return Ne},server_data_node:de(I,P==null?void 0:P.server)})});for(const d of V)d.catch(()=>{});const U=[];for(let d=0;d<v.length;d+=1)if(v[d])try{U.push(await V[d])}catch(L){if(L instanceof ze)return{type:"redirect",location:L.location};let P=500,I;for(A!=null&&A.includes(L)?(P=(Q=L.status)!=null?Q:P,I=L.error):L instanceof ye?(P=L.status,I=L.body):I=Me(L,{params:c,url:m,routeId:p.id});d--;)if(h[d]){let ae,x=d;for(;!U[x];)x-=1;try{return ae={node:await h[d](),loader:h[d],data:{},server:null,shared:null},await re({url:m,params:c,branch:U.slice(0,x+1).concat(ae),status:P,error:I,route:p})}catch{continue}}await ne(m);return}else U.push(void 0);return await re({url:m,params:c,branch:U,status:200,error:null,route:p,form:f?void 0:null})}async function oe({status:o,error:f,url:m,routeId:c}){var v;const p={},h=await ke();let g=null;if(h.server)try{const b=await Ge(m,[!0]);if(b.type!=="data"||b.nodes[0]&&b.nodes[0].type!=="data")throw 0;g=(v=b.nodes[0])!=null?v:null}catch{await ne(m);return}const $=await pe({loader:ke,url:m,params:p,routeId:c,parent:()=>Promise.resolve({}),server_data_node:de(g)}),E={node:await Re(),loader:Re,shared:null,server:null,data:null};return await re({url:m,params:p,branch:[$,E],status:o,error:f instanceof ye?f.body:Me(f,{url:m,params:p,routeId:null}),route:null})}function me(o,f){if(je(o))return;const m=decodeURI(o.pathname.slice(e.length)||"/");for(const c of se){const p=c.exec(m);if(p){const h=new URL(o.origin+lt(o.pathname,r)+o.search+o.hash);return{id:h.pathname+h.search,invalidating:f,route:c,params:ct(p),url:h}}}}function je(o){return o.origin!==location.origin||!o.pathname.startsWith(e)}async function he({url:o,scroll:f,keepfocus:m,redirect_chain:c,details:p,type:h,delta:g,accepted:$,blocked:E}){var O,V,U,Q;let v=!1;const b=me(o,!1),k={from:le("from",{params:t.params,routeId:(V=(O=t.route)==null?void 0:O.id)!=null?V:null,url:t.url}),to:le("to",{params:(U=b==null?void 0:b.params)!=null?U:null,routeId:(Q=b==null?void 0:b.route.id)!=null?Q:null,url:o}),type:h};g!==void 0&&(k.delta=g);const A={...k,cancel:()=>{v=!0}};if(u.before_navigate.forEach(d=>d(A)),v){E();return}ve(y),$(),s&&ee.navigating.set(k),await Se(b,o,c,{scroll:f,keepfocus:m,details:p},()=>{u.after_navigate.forEach(d=>d(k)),ee.navigating.set(null)})}function ne(o){return location.href=o.href,new Promise(()=>{})}return{after_navigate:o=>{$e(()=>(u.after_navigate.push(o),()=>{const f=u.after_navigate.indexOf(o);u.after_navigate.splice(f,1)}))},before_navigate:o=>{$e(()=>(u.before_navigate.push(o),()=>{const f=u.before_navigate.indexOf(o);u.before_navigate.splice(f,1)}))},disable_scroll_handling:()=>{(_||!s)&&(l=!1)},goto:(o,f={})=>_e(o,f,[]),invalidate:o=>{if(o===void 0)throw new Error("`invalidate()` (with no arguments) has been replaced by `invalidateAll()`");if(typeof o=="function")i.push(o);else{const{href:f}=new URL(o,location.href);i.push(m=>m.href===f)}return Pe()},invalidateAll:()=>(N=!0,Pe()),prefetch:async o=>{const f=new URL(o,Be(document));await Ie(f)},prefetch_routes:async o=>{const m=(o?se.filter(c=>o.some(p=>c.exec(p))):se).map(c=>Promise.all([...c.layouts,c.leaf].map(p=>p==null?void 0:p[1]())));await Promise.all(m)},apply_action:async o=>{if(o.type==="error"){const f=new URL(location.href),{branch:m,route:c}=t;if(!c)return;let p=t.branch.length;for(;p--;)if(c.errors[p]){let h,g=p;for(;!m[g];)g-=1;try{h={node:await c.errors[p](),loader:c.errors[p],data:{},server:null,shared:null};const $=await re({url:f,params:t.params,branch:m.slice(0,g+1).concat(h),status:500,error:o.error,route:c});t=$.state;const E=ce();F.$set($.props),E();return}catch{continue}}}else if(o.type==="redirect")_e(o.location,{},[]);else{const f={form:o.data};o.status!==Y.status&&(f.page={...Y,status:o.status});const m=ce();F.$set(f),m()}},_start_router:()=>{history.scrollRestoration="manual",addEventListener("beforeunload",c=>{var g,$;let p=!1;const h={from:le("from",{params:t.params,routeId:($=(g=t.route)==null?void 0:g.id)!=null?$:null,url:t.url}),to:null,type:"unload",cancel:()=>p=!0};u.before_navigate.forEach(E=>E(h)),p?(c.preventDefault(),c.returnValue=""):history.scrollRestoration="auto"}),addEventListener("visibilitychange",()=>{if(document.visibilityState==="hidden"){ve(y);try{sessionStorage[He]=JSON.stringify(ie)}catch{}}});const o=c=>{const{url:p,options:h}=Je(c);if(p&&h.prefetch){if(je(p))return;Ie(p)}};let f;const m=c=>{clearTimeout(f),f=setTimeout(()=>{var p;(p=c.target)==null||p.dispatchEvent(new CustomEvent("sveltekit:trigger_prefetch",{bubbles:!0}))},20)};addEventListener("touchstart",o),addEventListener("mousemove",m),addEventListener("sveltekit:trigger_prefetch",o),addEventListener("click",c=>{if(c.button||c.which!==1||c.metaKey||c.ctrlKey||c.shiftKey||c.altKey||c.defaultPrevented)return;const{a:p,url:h,options:g}=Je(c);if(!p||!h)return;const $=p instanceof SVGAElement;if(!$&&!(h.protocol==="https:"||h.protocol==="http:"))return;const E=(p.getAttribute("rel")||"").split(/\s+/);if(p.hasAttribute("download")||E.includes("external")||g.reload||($?p.target.baseVal:p.target))return;const[v,b]=h.href.split("#");if(b!==void 0&&v===location.href.split("#")[0]){X=!0,ve(y),ee.page.set({...Y,url:h}),ee.page.notify();return}he({url:h,scroll:g.noscroll?Ee():null,keepfocus:!1,redirect_chain:[],details:{state:{},replaceState:h.href===location.href},accepted:()=>c.preventDefault(),blocked:()=>c.preventDefault(),type:"link"})}),addEventListener("popstate",c=>{if(c.state){if(c.state[Z]===y)return;const p=c.state[Z]-y;he({url:new URL(location.href),scroll:ie[c.state[Z]],keepfocus:!1,redirect_chain:[],details:null,accepted:()=>{y=c.state[Z]},blocked:()=>{history.go(-p)},type:"popstate",delta:p})}}),addEventListener("hashchange",()=>{X&&(X=!1,history.replaceState({...history.state,[Z]:++y},"",location.href))});for(const c of document.querySelectorAll("link"))c.rel==="icon"&&(c.href=c.href);addEventListener("pageshow",c=>{c.persisted&&ee.navigating.set(null)})},_hydrate:async({status:o,error:f,node_ids:m,params:c,routeId:p,data:h,form:g})=>{var v;const $=new URL(location.href);let E;try{const b=m.map(async(k,A)=>{const O=h[A];return pe({loader:ue[k],url:$,params:c,routeId:p,parent:async()=>{const V={};for(let U=0;U<A;U+=1)Object.assign(V,(await b[U]).data);return V},server_data_node:de(O)})});E=await re({url:$,params:c,branch:await Promise.all(b),status:o,error:f,form:g,route:(v=se.find(k=>k.id===p))!=null?v:null})}catch(b){const k=b;if(k instanceof ze){await ne(new URL(b.location,location.href));return}E=await oe({status:k instanceof ye?k.status:500,error:k,url:$,routeId:p})}Ae(E)}}}let Ct=1;async function Ge(n,e){const r=new URL(n);r.pathname=n.pathname.replace(/\/$/,"")+Nt,r.searchParams.set("__invalid",e.map(a=>a?"y":"n").join("")),r.searchParams.set("__id",String(Ct++)),await S(()=>import(r.href),[],import.meta.url);const i=window.__sveltekit_data;return delete window.__sveltekit_data,i}function Me(n,e){var r;return(r=Ut.handleError({error:n,event:e}))!=null?r:{error:"Internal Error"}}const Bt=["hash","href","host","hostname","origin","pathname","port","protocol","search","searchParams","toString","toJSON"];function le(n,e){for(const r of Bt)Object.defineProperty(e,r,{get(){throw new Error(`The navigation shape changed - ${n}.${r} should now be ${n}.url.${r}`)}});return e}function ce(){return()=>{}}async function Ft({env:n,hydrate:e,paths:r,target:i,trailing_slash:a}){ot(r);const u=qt({target:i,base:r.base,trailing_slash:a});st({client:u}),e?await u._hydrate(e):u.goto(location.href,{replaceState:!0}),u._start_router()}export{Ft as start};
