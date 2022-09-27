import{_ as O}from"./chunks/preload-helper-aa6bc0ce.js";import{S as He,i as We,s as Xe,a as Ye,e as T,c as Qe,b as z,g as F,t as D,d as M,f as V,h as q,j as Ze,o as ve,k as xe,l as et,m as tt,n as we,p as H,q as rt,r as nt,u as at,v as C,w as ee,x as B,y as J,z as qe}from"./chunks/index-c260c7ce.js";import{g as Ce,f as Be,s as x,a as $e,b as it,i as ot}from"./chunks/singletons-bb603cec.js";class be{constructor(e,r){this.status=e,typeof r=="string"?this.body={message:r}:r?this.body=r:this.body={message:`Error: ${e}`}}toString(){return JSON.stringify(this.body)}}class Je{constructor(e,r){this.status=e,this.location=r}}function st(n,e){return n==="/"||e==="ignore"?n:e==="never"?n.endsWith("/")?n.slice(0,-1):n:e==="always"&&!n.endsWith("/")?n+"/":n}function lt(n){for(const e in n)n[e]=n[e].replace(/%23/g,"#").replace(/%3[Bb]/g,";").replace(/%2[Cc]/g,",").replace(/%2[Ff]/g,"/").replace(/%3[Ff]/g,"?").replace(/%3[Aa]/g,":").replace(/%40/g,"@").replace(/%26/g,"&").replace(/%3[Dd]/g,"=").replace(/%2[Bb]/g,"+").replace(/%24/g,"$");return n}const ct=["href","pathname","search","searchParams","toString","toJSON"];function ft(n,e){const r=new URL(n);for(const i of ct){let a=r[i];Object.defineProperty(r,i,{get(){return e(),a},enumerable:!0,configurable:!0})}return r[Symbol.for("nodejs.util.inspect.custom")]=(i,a,u)=>u(n,a),ut(r),r}function ut(n){Object.defineProperty(n,"hash",{get(){throw new Error("Cannot access event.url.hash. Consider using `$page.url.hash` inside a component instead")}})}function _t(n){let e=5381,r=n.length;if(typeof n=="string")for(;r;)e=e*33^n.charCodeAt(--r);else for(;r;)e=e*33^n[--r];return(e>>>0).toString(36)}const Re=window.fetch;window.fetch=(n,e)=>{if((n instanceof Request?n.method:(e==null?void 0:e.method)||"GET")!=="GET"){const i=new URL(n instanceof Request?n.url:n.toString(),document.baseURI).href;ce.delete(i)}return Re(n,e)};const ce=new Map;function dt(n,e,r){let a=`script[data-sveltekit-fetched][data-url=${JSON.stringify(typeof n=="string"?n:n.url)}]`;r&&typeof r.body=="string"&&(a+=`[data-hash="${_t(r.body)}"]`);const u=document.querySelector(a);if(u!=null&&u.textContent){const{body:t,...s}=JSON.parse(u.textContent),l=u.getAttribute("data-ttl");return l&&ce.set(e,{body:t,init:s,ttl:1e3*Number(l)}),Promise.resolve(new Response(t,s))}return Re(n,r)}function pt(n,e){const r=ce.get(n);if(r){if(performance.now()<r.ttl)return new Response(r.body,r.init);ce.delete(n)}return Re(n,e)}const mt=/^(\.\.\.)?(\w+)(?:=(\w+))?$/;function ht(n){const e=[],r=[];let i=!0;return{pattern:n===""?/^\/$/:new RegExp(`^${n.split(/(?:\/|$)/).filter(gt).map((u,t,s)=>{const l=decodeURIComponent(u),_=/^\[\.\.\.(\w+)(?:=(\w+))?\]$/.exec(l);if(_)return e.push(_[1]),r.push(_[2]),"(?:/(.*))?";const w=t===s.length-1;return l&&"/"+l.split(/\[(.+?)\]/).map((R,N)=>{if(N%2){const K=mt.exec(R);if(!K)throw new Error(`Invalid param: ${R}. Params and matcher names can only have underscores and alphanumeric characters.`);const[,y,G,W]=K;return e.push(G),r.push(W),y?"(.*?)":"([^/]+?)"}return w&&R.includes(".")&&(i=!1),R.normalize().replace(/%5[Bb]/g,"[").replace(/%5[Dd]/g,"]").replace(/#/g,"%23").replace(/\?/g,"%3F").replace(/[.*+?^${}()|[\]\\]/g,"\\$&")}).join("")}).join("")}${i?"/?":""}$`),names:e,types:r}}function gt(n){return!/^\([^)]+\)$/.test(n)}function wt(n,e,r,i){const a={};for(let u=0;u<e.length;u+=1){const t=e[u],s=r[u],l=n[u+1]||"";if(s){const _=i[s];if(!_)throw new Error(`Missing "${s}" param matcher`);if(!_(l))return}a[t]=l}return a}function bt(n,e,r,i){const a=new Set(e);return Object.entries(r).map(([s,[l,_,w]])=>{const{pattern:R,names:N,types:K}=ht(s),y={id:s,exec:G=>{const W=R.exec(G);if(W)return wt(W,N,K,i)},errors:[1,...w||[]].map(G=>n[G]),layouts:[0,..._||[]].map(t),leaf:u(l)};return y.errors.length=y.layouts.length=Math.max(y.errors.length,y.layouts.length),y});function u(s){const l=s<0;return l&&(s=~s),[l,n[s]]}function t(s){return s===void 0?s:[a.has(s),n[s]]}}function yt(n){let e,r,i;var a=n[0][0];function u(t){return{props:{data:t[2],form:t[1]}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&4&&(l.data=t[2]),s&2&&(l.form=t[1]),a!==(a=t[0][0])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function vt(n){let e,r,i;var a=n[0][0];function u(t){return{props:{data:t[2],$$slots:{default:[Ot]},$$scope:{ctx:t}}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&4&&(l.data=t[2]),s&2107&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][0])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function $t(n){let e,r,i;var a=n[0][1];function u(t){return{props:{data:t[3],form:t[1]}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&8&&(l.data=t[3]),s&2&&(l.form=t[1]),a!==(a=t[0][1])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function Et(n){let e,r,i;var a=n[0][1];function u(t){return{props:{data:t[3],$$slots:{default:[Pt]},$$scope:{ctx:t}}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&8&&(l.data=t[3]),s&2099&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][1])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function kt(n){let e,r,i;var a=n[0][2];function u(t){return{props:{data:t[4],form:t[1]}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&16&&(l.data=t[4]),s&2&&(l.form=t[1]),a!==(a=t[0][2])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function Rt(n){let e,r,i;var a=n[0][2];function u(t){return{props:{data:t[4],$$slots:{default:[Lt]},$$scope:{ctx:t}}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&16&&(l.data=t[4]),s&2083&&(l.$$scope={dirty:s,ctx:t}),a!==(a=t[0][2])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function Lt(n){let e,r,i;var a=n[0][3];function u(t){return{props:{data:t[5],form:t[1]}}}return a&&(e=new a(u(n))),{c(){e&&C(e.$$.fragment),r=T()},l(t){e&&ee(e.$$.fragment,t),r=T()},m(t,s){e&&B(e,t,s),z(t,r,s),i=!0},p(t,s){const l={};if(s&32&&(l.data=t[5]),s&2&&(l.form=t[1]),a!==(a=t[0][3])){if(e){F();const _=e;D(_.$$.fragment,1,0,()=>{J(_,1)}),M()}a?(e=new a(u(t)),C(e.$$.fragment),V(e.$$.fragment,1),B(e,r.parentNode,r)):e=null}else a&&e.$set(l)},i(t){i||(e&&V(e.$$.fragment,t),i=!0)},o(t){e&&D(e.$$.fragment,t),i=!1},d(t){t&&q(r),e&&J(e,t)}}}function Pt(n){let e,r,i,a;const u=[Rt,kt],t=[];function s(l,_){return l[0][3]?0:1}return e=s(n),r=t[e]=u[e](n),{c(){r.c(),i=T()},l(l){r.l(l),i=T()},m(l,_){t[e].m(l,_),z(l,i,_),a=!0},p(l,_){let w=e;e=s(l),e===w?t[e].p(l,_):(F(),D(t[w],1,1,()=>{t[w]=null}),M(),r=t[e],r?r.p(l,_):(r=t[e]=u[e](l),r.c()),V(r,1),r.m(i.parentNode,i))},i(l){a||(V(r),a=!0)},o(l){D(r),a=!1},d(l){t[e].d(l),l&&q(i)}}}function Ot(n){let e,r,i,a;const u=[Et,$t],t=[];function s(l,_){return l[0][2]?0:1}return e=s(n),r=t[e]=u[e](n),{c(){r.c(),i=T()},l(l){r.l(l),i=T()},m(l,_){t[e].m(l,_),z(l,i,_),a=!0},p(l,_){let w=e;e=s(l),e===w?t[e].p(l,_):(F(),D(t[w],1,1,()=>{t[w]=null}),M(),r=t[e],r?r.p(l,_):(r=t[e]=u[e](l),r.c()),V(r,1),r.m(i.parentNode,i))},i(l){a||(V(r),a=!0)},o(l){D(r),a=!1},d(l){t[e].d(l),l&&q(i)}}}function ze(n){let e,r=n[7]&&Ke(n);return{c(){e=xe("div"),r&&r.c(),this.h()},l(i){e=et(i,"DIV",{id:!0,"aria-live":!0,"aria-atomic":!0,style:!0});var a=tt(e);r&&r.l(a),a.forEach(q),this.h()},h(){we(e,"id","svelte-announcer"),we(e,"aria-live","assertive"),we(e,"aria-atomic","true"),H(e,"position","absolute"),H(e,"left","0"),H(e,"top","0"),H(e,"clip","rect(0 0 0 0)"),H(e,"clip-path","inset(50%)"),H(e,"overflow","hidden"),H(e,"white-space","nowrap"),H(e,"width","1px"),H(e,"height","1px")},m(i,a){z(i,e,a),r&&r.m(e,null)},p(i,a){i[7]?r?r.p(i,a):(r=Ke(i),r.c(),r.m(e,null)):r&&(r.d(1),r=null)},d(i){i&&q(e),r&&r.d()}}}function Ke(n){let e;return{c(){e=rt(n[8])},l(r){e=nt(r,n[8])},m(r,i){z(r,e,i)},p(r,i){i&256&&at(e,r[8])},d(r){r&&q(e)}}}function St(n){let e,r,i,a,u;const t=[vt,yt],s=[];function l(w,R){return w[0][1]?0:1}e=l(n),r=s[e]=t[e](n);let _=n[6]&&ze(n);return{c(){r.c(),i=Ye(),_&&_.c(),a=T()},l(w){r.l(w),i=Qe(w),_&&_.l(w),a=T()},m(w,R){s[e].m(w,R),z(w,i,R),_&&_.m(w,R),z(w,a,R),u=!0},p(w,[R]){let N=e;e=l(w),e===N?s[e].p(w,R):(F(),D(s[N],1,1,()=>{s[N]=null}),M(),r=s[e],r?r.p(w,R):(r=s[e]=t[e](w),r.c()),V(r,1),r.m(i.parentNode,i)),w[6]?_?_.p(w,R):(_=ze(w),_.c(),_.m(a.parentNode,a)):_&&(_.d(1),_=null)},i(w){u||(V(r),u=!0)},o(w){D(r),u=!1},d(w){s[e].d(w),w&&q(i),_&&_.d(w),w&&q(a)}}}function It(n,e,r){let{stores:i}=e,{page:a}=e,{components:u}=e,{form:t}=e,{data_0:s=null}=e,{data_1:l=null}=e,{data_2:_=null}=e,{data_3:w=null}=e;Ze(i.page.notify);let R=!1,N=!1,K=null;return ve(()=>{const y=i.page.subscribe(()=>{R&&(r(7,N=!0),r(8,K=document.title||"untitled page"))});return r(6,R=!0),y}),n.$$set=y=>{"stores"in y&&r(9,i=y.stores),"page"in y&&r(10,a=y.page),"components"in y&&r(0,u=y.components),"form"in y&&r(1,t=y.form),"data_0"in y&&r(2,s=y.data_0),"data_1"in y&&r(3,l=y.data_1),"data_2"in y&&r(4,_=y.data_2),"data_3"in y&&r(5,w=y.data_3)},n.$$.update=()=>{n.$$.dirty&1536&&i.page.set(a)},[u,t,s,l,_,w,R,N,K,i,a]}class At extends He{constructor(e){super(),We(this,e,It,St,Xe,{stores:9,page:10,components:0,form:1,data_0:2,data_1:3,data_2:4,data_3:5})}}const Tt={},fe=[()=>O(()=>import("./chunks/0-16ef5bb2.js"),["chunks/0-16ef5bb2.js","chunks/_layout-d02ecff8.js","components/pages/_layout.svelte-288baa76.js","assets/_layout-9ff9c8f1.css","chunks/index-c260c7ce.js","chunks/messages-0552e578.js","chunks/index-49b214d9.js","chunks/sweetalert2.all-93f16b68.js","chunks/index-b31b03a2.js"],import.meta.url),()=>O(()=>import("./chunks/1-3335a1d9.js"),["chunks/1-3335a1d9.js","components/error.svelte-9bbf465b.js","chunks/index-c260c7ce.js","chunks/singletons-bb603cec.js","chunks/index-49b214d9.js"],import.meta.url),()=>O(()=>import("./chunks/2-c9614fd9.js"),["chunks/2-c9614fd9.js","components/pages/(main)/_layout.svelte-fc44aa74.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/3-de8c366d.js"),["chunks/3-de8c366d.js","components/pages/(main)/Modus/_layout.svelte-f0ec9228.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/4-833e7532.js"),["chunks/4-833e7532.js","components/pages/(main)/Plugins/_layout.svelte-ca61074b.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/5-b604bf3d.js"),["chunks/5-b604bf3d.js","components/pages/(main)/Settings/_layout.svelte-adf6b330.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/6-145dd096.js"),["chunks/6-145dd096.js","components/pages/(main)/Toolbox/_layout.svelte-b3152bbd.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/7-6aaa1c2e.js"),["chunks/7-6aaa1c2e.js","components/pages/(main)/server/_server_/_layout.svelte-0381627e.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/8-582f5e49.js"),["chunks/8-582f5e49.js","chunks/_page-9468531c.js","components/pages/(main)/_page.svelte-6e60f75c.js","chunks/index-c260c7ce.js","chunks/sweetalert2.all-93f16b68.js","chunks/messages-0552e578.js","chunks/index-49b214d9.js"],import.meta.url),()=>O(()=>import("./chunks/9-48585d74.js"),["chunks/9-48585d74.js","components/pages/(main)/Modus/_page.svelte-98976bd4.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/10-7d8c2c5d.js"),["chunks/10-7d8c2c5d.js","components/pages/(main)/Plugins/_page.svelte-8fd01ded.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/11-706f8869.js"),["chunks/11-706f8869.js","components/pages/(main)/Settings/_page.svelte-2c1d7065.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/12-e7d0b7db.js"),["chunks/12-e7d0b7db.js","components/pages/(main)/Settings/audio/_page.svelte-1cc979c0.js","assets/_page-91244bf2.css","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/13-8efb2109.js"),["chunks/13-8efb2109.js","components/pages/(main)/Settings/language/_page.svelte-b663597f.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/14-209b8e8d.js"),["chunks/14-209b8e8d.js","components/pages/(main)/Settings/overlay/_page.svelte-78079d0b.js","chunks/index-c260c7ce.js","chunks/index-b31b03a2.js"],import.meta.url),()=>O(()=>import("./chunks/15-c201d77d.js"),["chunks/15-c201d77d.js","components/pages/(main)/Settings/video/_page.svelte-fd483e58.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/16-6520ae40.js"),["chunks/16-6520ae40.js","components/pages/(main)/Toolbox/_page.svelte-a9a7f0d6.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/17-0f4239ae.js"),["chunks/17-0f4239ae.js","components/pages/(main)/dm/_page.svelte-cceeee7a.js","chunks/preload-helper-aa6bc0ce.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/18-4431bd0f.js"),["chunks/18-4431bd0f.js","components/pages/(main)/dm/_user_/_page.svelte-5d44ade5.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/19-0da33530.js"),["chunks/19-0da33530.js","components/pages/(main)/profile/_page.svelte-4b47a41b.js","chunks/preload-helper-aa6bc0ce.js","chunks/index-c260c7ce.js","chunks/sweetalert2.all-93f16b68.js"],import.meta.url),()=>O(()=>import("./chunks/20-9f8ea15e.js"),["chunks/20-9f8ea15e.js","components/pages/(main)/server/_server_/_page.svelte-57dd8361.js","chunks/index-c260c7ce.js"],import.meta.url),()=>O(()=>import("./chunks/21-4d49d384.js"),["chunks/21-4d49d384.js","components/pages/(main)/server/_server_/channel/_channel_/_page.svelte-4e9b2b2b.js","chunks/index-c260c7ce.js"],import.meta.url)],Dt=[],Vt={"(main)":[8,[2]],"(main)/Modus":[9,[2,3]],"(main)/Plugins":[10,[2,4]],"(main)/Settings":[11,[2,5]],"(main)/Toolbox":[16,[2,6]],"(main)/dm":[17,[2]],"(main)/profile":[19,[2]],"(main)/Settings/audio":[12,[2,5]],"(main)/Settings/language":[13,[2,5]],"(main)/Settings/overlay":[14,[2,5]],"(main)/Settings/video":[15,[2,5]],"(main)/dm/[user]":[18,[2]],"(main)/server/[server]":[20,[2,7]],"(main)/server/[server]/channel/[channel]":[21,[2,7]]},jt={handleError:({error:n})=>(console.error(n),{message:"Internal Error"})},Ut="/__data.js",Ge="sveltekit:scroll",Q="sveltekit:index",oe=bt(fe,Dt,Vt,Tt),Ee=fe[0],ke=fe[1];Ee();ke();let ae={};try{ae=JSON.parse(sessionStorage[Ge])}catch{}function ye(n){ae[n]=$e()}function Nt({target:n,base:e,trailing_slash:r}){var Ve;const i=[],a={id:null,promise:null},u={before_navigate:[],after_navigate:[]};let t={branch:[],error:null,session_id:0,url:null},s=!1,l=!0,_=!1,w=1,R=null,N=!1,K,y=(Ve=history.state)==null?void 0:Ve[Q];y||(y=Date.now(),history.replaceState({...history.state,[Q]:y},"",location.href));const G=ae[y];G&&(history.scrollRestoration="manual",scrollTo(G.x,G.y));let W=!1,X,Le;function Pe(){if(!R){const o=new URL(location.href);R=Promise.resolve().then(async()=>{const f=pe(o,!0);await Se(f,o,[]),R=null,N=!1})}return R}async function ue(o,{noscroll:f=!1,replaceState:m=!1,keepfocus:c=!1,state:d={}},h){return typeof o=="string"&&(o=new URL(o,Ce(document))),me({url:o,scroll:f?$e():null,keepfocus:c,redirect_chain:h,details:{state:d,replaceState:m},accepted:()=>{},blocked:()=>{},type:"goto"})}async function Oe(o){const f=pe(o,!1);if(!f)throw new Error("Attempted to prefetch a URL that does not belong to this app");return a.promise=Te(f),a.id=f.id,a.promise}async function Se(o,f,m,c,d){var $,E;const h=Le={};let g=o&&await Te(o);if(!g&&f.origin===location.origin&&f.pathname===location.pathname&&(g=await ie({status:404,error:new Error(`Not found: ${f.pathname}`),url:f,routeId:null})),!g)return await re(f),!1;if(f=(o==null?void 0:o.url)||f,Le!==h)return!1;if(i.length=0,g.type==="redirect")if(m.length>10||m.includes(f.pathname))g=await ie({status:500,error:new Error("Redirect loop"),url:f,routeId:null});else return ue(new URL(g.location,f).href,{},[...m,f.pathname]),!1;else((E=($=g.props)==null?void 0:$.page)==null?void 0:E.status)>=400&&await x.updated.check()&&await re(f);if(_=!0,c&&c.details){const{details:v}=c,b=v.replaceState?0:1;v.state[Q]=y+=b,history[v.replaceState?"replaceState":"pushState"](v.state,"",f)}if(s){t=g.state,g.props.page&&(g.props.page.url=f);const v=le();K.$set(g.props),v()}else Ie(g);if(c){const{scroll:v,keepfocus:b}=c;if(!b){const k=document.body,A=k.getAttribute("tabindex");k.tabIndex=-1,k.focus({preventScroll:!0}),setTimeout(()=>{var P;(P=getSelection())==null||P.removeAllRanges()}),A!==null?k.setAttribute("tabindex",A):k.removeAttribute("tabindex")}if(await qe(),l){const k=f.hash&&document.getElementById(f.hash.slice(1));v?scrollTo(v.x,v.y):k?k.scrollIntoView():scrollTo(0,0)}}else await qe();a.promise=null,a.id=null,l=!0,g.props.page&&(X=g.props.page),d&&d(),_=!1}function Ie(o){var d,h;t=o.state;const f=document.querySelector("style[data-sveltekit]");f&&f.remove(),X=o.props.page;const m=le();K=new At({target:n,props:{...o.props,stores:x},hydrate:!0}),m();const c={from:null,to:se("to",{params:t.params,routeId:(h=(d=t.route)==null?void 0:d.id)!=null?h:null,url:new URL(location.href)}),type:"load"};u.after_navigate.forEach(g=>g(c)),s=!0}async function te({url:o,params:f,branch:m,status:c,error:d,route:h,form:g}){var A;const $=m.filter(Boolean),E={type:"loaded",state:{url:o,params:f,branch:m,error:d,route:h,session_id:w},props:{components:$.map(P=>P.node.component)}};g!==void 0&&(E.props.form=g);let v={},b=!X;for(let P=0;P<$.length;P+=1){const j=$[P];v={...v,...j.data},(b||!t.branch.some(U=>U===j))&&(E.props[`data_${P}`]=v,b=b||Object.keys((A=j.data)!=null?A:{}).length>0)}if(b||(b=Object.keys(X.data).length!==Object.keys(v).length),!t.url||o.href!==t.url.href||t.error!==d||b){E.props.page={error:d,params:f,routeId:h&&h.id,status:c,url:o,data:b?v:X.data};const P=(j,U)=>{Object.defineProperty(E.props.page,j,{get:()=>{throw new Error(`$page.${j} has been replaced by $page.url.${U}`)}})};P("origin","origin"),P("path","pathname"),P("query","searchParams")}return E}async function _e({loader:o,parent:f,url:m,params:c,routeId:d,server_data_node:h}){var v,b,k,A,P;let g=null;const $={dependencies:new Set,params:new Set,parent:!1,url:!1},E=await o();if((v=E.shared)!=null&&v.load){let j=function(...p){for(const L of p){const{href:S}=new URL(L,m);$.dependencies.add(S)}};const U={};for(const p in c)Object.defineProperty(U,p,{get(){return $.params.add(p),c[p]},enumerable:!0});const Y={routeId:d,params:U,data:(b=h==null?void 0:h.data)!=null?b:null,url:ft(m,()=>{$.url=!0}),async fetch(p,L){let S;typeof p=="string"?S=p:(S=p.url,L={body:p.method==="GET"||p.method==="HEAD"?void 0:await p.blob(),cache:p.cache,credentials:p.credentials,headers:p.headers,integrity:p.integrity,keepalive:p.keepalive,method:p.method,mode:p.mode,redirect:p.redirect,referrer:p.referrer,referrerPolicy:p.referrerPolicy,signal:p.signal,...L});const I=new URL(S,m).href;return j(I),s?pt(I,L):dt(S,I,L)},setHeaders:()=>{},depends:j,parent(){return $.parent=!0,f()}};Object.defineProperties(Y,{props:{get(){throw new Error("@migration task: Replace `props` with `data` stuff https://github.com/sveltejs/kit/discussions/5774#discussioncomment-3292693")},enumerable:!1},session:{get(){throw new Error("session is no longer available. See https://github.com/sveltejs/kit/discussions/5883")},enumerable:!1},stuff:{get(){throw new Error("@migration task: Remove stuff https://github.com/sveltejs/kit/discussions/5774#discussioncomment-3292693")},enumerable:!1}}),g=(k=await E.shared.load.call(null,Y))!=null?k:null}return{node:E,loader:o,server:h,shared:(A=E.shared)!=null&&A.load?{type:"data",data:g,uses:$}:null,data:(P=g!=null?g:h==null?void 0:h.data)!=null?P:null}}function Ae(o,f,m){if(N)return!0;if(!m)return!1;if(m.parent&&f||o.url&&m.url)return!0;for(const c of o.params)if(m.params.has(c))return!0;for(const c of m.dependencies)if(i.some(d=>d(new URL(c))))return!0;return!1}function de(o,f){var m,c;return(o==null?void 0:o.type)==="data"?{type:"data",data:o.data,uses:{dependencies:new Set((m=o.uses.dependencies)!=null?m:[]),params:new Set((c=o.uses.params)!=null?c:[]),parent:!!o.uses.parent,url:!!o.uses.url}}:(o==null?void 0:o.type)==="skip"&&f!=null?f:null}async function Te({id:o,invalidating:f,url:m,params:c,route:d}){var Y;if(a.id===o&&a.promise)return a.promise;const{errors:h,layouts:g,leaf:$}=d,E=t.url&&{url:o!==t.url.pathname+t.url.search,params:Object.keys(c).filter(p=>t.params[p]!==c[p])},v=[...g,$];h.forEach(p=>p==null?void 0:p().catch(()=>{})),v.forEach(p=>p==null?void 0:p[1]().catch(()=>{}));let b=null;const k=v.reduce((p,L,S)=>{var Z;const I=t.branch[S],ne=!!(L!=null&&L[0])&&((I==null?void 0:I.loader)!==L[1]||Ae(E,p.some(Boolean),(Z=I.server)==null?void 0:Z.uses));return p.push(ne),p},[]);if(k.some(Boolean)){try{b=await Fe(m,k)}catch(p){return ie({status:500,error:p,url:m,routeId:d.id})}if(b.type==="redirect")return b}const A=b==null?void 0:b.nodes;let P=!1;const j=v.map(async(p,L)=>{var he,je;if(!p)return;const S=t.branch[L],I=(he=A==null?void 0:A[L])!=null?he:null;if((!I||I.type==="skip")&&p[1]===(S==null?void 0:S.loader)&&!Ae(E,P,(je=S.shared)==null?void 0:je.uses))return S;if(P=!0,(I==null?void 0:I.type)==="error")throw I;return _e({loader:p[1],url:m,params:c,routeId:d.id,parent:async()=>{var Ne;const Ue={};for(let ge=0;ge<L;ge+=1)Object.assign(Ue,(Ne=await j[ge])==null?void 0:Ne.data);return Ue},server_data_node:de(I,S==null?void 0:S.server)})});for(const p of j)p.catch(()=>{});const U=[];for(let p=0;p<v.length;p+=1)if(v[p])try{U.push(await j[p])}catch(L){if(L instanceof Je)return{type:"redirect",location:L.location};let S=500,I;for(A!=null&&A.includes(L)?(S=(Y=L.status)!=null?Y:S,I=L.error):L instanceof be?(S=L.status,I=L.body):I=Me(L,{params:c,url:m,routeId:d.id});p--;)if(h[p]){let ne,Z=p;for(;!U[Z];)Z-=1;try{return ne={node:await h[p](),loader:h[p],data:{},server:null,shared:null},await te({url:m,params:c,branch:U.slice(0,Z+1).concat(ne),status:S,error:I,route:d})}catch{continue}}await re(m);return}else U.push(void 0);return await te({url:m,params:c,branch:U,status:200,error:null,route:d,form:f?void 0:null})}async function ie({status:o,error:f,url:m,routeId:c}){var v;const d={},h=await Ee();let g=null;if(h.server)try{const b=await Fe(m,[!0]);if(b.type!=="data"||b.nodes[0]&&b.nodes[0].type!=="data")throw 0;g=(v=b.nodes[0])!=null?v:null}catch{await re(m);return}const $=await _e({loader:Ee,url:m,params:d,routeId:c,parent:()=>Promise.resolve({}),server_data_node:de(g)}),E={node:await ke(),loader:ke,shared:null,server:null,data:null};return await te({url:m,params:d,branch:[$,E],status:o,error:f instanceof be?f.body:Me(f,{url:m,params:d,routeId:null}),route:null})}function pe(o,f){if(De(o))return;const m=decodeURI(o.pathname.slice(e.length)||"/");for(const c of oe){const d=c.exec(m);if(d){const h=new URL(o.origin+st(o.pathname,r)+o.search+o.hash);return{id:h.pathname+h.search,invalidating:f,route:c,params:lt(d),url:h}}}}function De(o){return o.origin!==location.origin||!o.pathname.startsWith(e)}async function me({url:o,scroll:f,keepfocus:m,redirect_chain:c,details:d,type:h,delta:g,accepted:$,blocked:E}){var P,j,U,Y;let v=!1;const b=pe(o,!1),k={from:se("from",{params:t.params,routeId:(j=(P=t.route)==null?void 0:P.id)!=null?j:null,url:t.url}),to:se("to",{params:(U=b==null?void 0:b.params)!=null?U:null,routeId:(Y=b==null?void 0:b.route.id)!=null?Y:null,url:o}),type:h};g!==void 0&&(k.delta=g);const A={...k,cancel:()=>{v=!0}};if(u.before_navigate.forEach(p=>p(A)),v){E();return}ye(y),$(),s&&x.navigating.set(k),await Se(b,o,c,{scroll:f,keepfocus:m,details:d},()=>{u.after_navigate.forEach(p=>p(k)),x.navigating.set(null)})}function re(o){return location.href=o.href,new Promise(()=>{})}return{after_navigate:o=>{ve(()=>(u.after_navigate.push(o),()=>{const f=u.after_navigate.indexOf(o);u.after_navigate.splice(f,1)}))},before_navigate:o=>{ve(()=>(u.before_navigate.push(o),()=>{const f=u.before_navigate.indexOf(o);u.before_navigate.splice(f,1)}))},disable_scroll_handling:()=>{(_||!s)&&(l=!1)},goto:(o,f={})=>ue(o,f,[]),invalidate:o=>{if(o===void 0)throw new Error("`invalidate()` (with no arguments) has been replaced by `invalidateAll()`");if(typeof o=="function")i.push(o);else{const{href:f}=new URL(o,location.href);i.push(m=>m.href===f)}return Pe()},invalidateAll:()=>(N=!0,Pe()),prefetch:async o=>{const f=new URL(o,Ce(document));await Oe(f)},prefetch_routes:async o=>{const m=(o?oe.filter(c=>o.some(d=>c.exec(d))):oe).map(c=>Promise.all([...c.layouts,c.leaf].map(d=>d==null?void 0:d[1]())));await Promise.all(m)},apply_action:async o=>{if(o.type==="error"){const f=new URL(location.href),{branch:m,route:c}=t;if(!c)return;let d=t.branch.length;for(;d--;)if(c.errors[d]){let h,g=d;for(;!m[g];)g-=1;try{h={node:await c.errors[d](),loader:c.errors[d],data:{},server:null,shared:null};const $=await te({url:f,params:t.params,branch:m.slice(0,g+1).concat(h),status:500,error:o.error,route:c});t=$.state;const E=le();K.$set($.props),E();return}catch{continue}}}else if(o.type==="redirect")ue(o.location,{},[]);else{const f={form:o.data};o.status!==X.status&&(f.page={...X,status:o.status});const m=le();K.$set(f),m()}},_start_router:()=>{history.scrollRestoration="manual",addEventListener("beforeunload",c=>{var g,$;let d=!1;const h={from:se("from",{params:t.params,routeId:($=(g=t.route)==null?void 0:g.id)!=null?$:null,url:t.url}),to:null,type:"unload",cancel:()=>d=!0};u.before_navigate.forEach(E=>E(h)),d?(c.preventDefault(),c.returnValue=""):history.scrollRestoration="auto"}),addEventListener("visibilitychange",()=>{if(document.visibilityState==="hidden"){ye(y);try{sessionStorage[Ge]=JSON.stringify(ae)}catch{}}});const o=c=>{const{url:d,options:h}=Be(c);if(d&&h.prefetch){if(De(d))return;Oe(d)}};let f;const m=c=>{clearTimeout(f),f=setTimeout(()=>{var d;(d=c.target)==null||d.dispatchEvent(new CustomEvent("sveltekit:trigger_prefetch",{bubbles:!0}))},20)};addEventListener("touchstart",o),addEventListener("mousemove",m),addEventListener("sveltekit:trigger_prefetch",o),addEventListener("click",c=>{if(c.button||c.which!==1||c.metaKey||c.ctrlKey||c.shiftKey||c.altKey||c.defaultPrevented)return;const{a:d,url:h,options:g}=Be(c);if(!d||!h)return;const $=d instanceof SVGAElement;if(!$&&!(h.protocol==="https:"||h.protocol==="http:"))return;const E=(d.getAttribute("rel")||"").split(/\s+/);if(d.hasAttribute("download")||E.includes("external")||g.reload||($?d.target.baseVal:d.target))return;const[v,b]=h.href.split("#");if(b!==void 0&&v===location.href.split("#")[0]){W=!0,ye(y),x.page.set({...X,url:h}),x.page.notify();return}me({url:h,scroll:g.noscroll?$e():null,keepfocus:!1,redirect_chain:[],details:{state:{},replaceState:h.href===location.href},accepted:()=>c.preventDefault(),blocked:()=>c.preventDefault(),type:"link"})}),addEventListener("popstate",c=>{if(c.state){if(c.state[Q]===y)return;const d=c.state[Q]-y;me({url:new URL(location.href),scroll:ae[c.state[Q]],keepfocus:!1,redirect_chain:[],details:null,accepted:()=>{y=c.state[Q]},blocked:()=>{history.go(-d)},type:"popstate",delta:d})}}),addEventListener("hashchange",()=>{W&&(W=!1,history.replaceState({...history.state,[Q]:++y},"",location.href))});for(const c of document.querySelectorAll("link"))c.rel==="icon"&&(c.href=c.href);addEventListener("pageshow",c=>{c.persisted&&x.navigating.set(null)})},_hydrate:async({status:o,error:f,node_ids:m,params:c,routeId:d,data:h,form:g})=>{var v;const $=new URL(location.href);let E;try{const b=m.map(async(k,A)=>{const P=h[A];return _e({loader:fe[k],url:$,params:c,routeId:d,parent:async()=>{const j={};for(let U=0;U<A;U+=1)Object.assign(j,(await b[U]).data);return j},server_data_node:de(P)})});E=await te({url:$,params:c,branch:await Promise.all(b),status:o,error:f,form:g,route:(v=oe.find(k=>k.id===d))!=null?v:null})}catch(b){const k=b;if(k instanceof Je){await re(new URL(b.location,location.href));return}E=await ie({status:k instanceof be?k.status:500,error:k,url:$,routeId:d})}Ie(E)}}}let qt=1;async function Fe(n,e){const r=new URL(n);r.pathname=n.pathname.replace(/\/$/,"")+Ut,r.searchParams.set("__invalid",e.map(a=>a?"y":"n").join("")),r.searchParams.set("__id",String(qt++)),await O(()=>import(r.href),[],import.meta.url);const i=window.__sveltekit_data;return delete window.__sveltekit_data,i}function Me(n,e){var r;return(r=jt.handleError({error:n,event:e}))!=null?r:{error:"Internal Error"}}const Ct=["hash","href","host","hostname","origin","pathname","port","protocol","search","searchParams","toString","toJSON"];function se(n,e){for(const r of Ct)Object.defineProperty(e,r,{get(){throw new Error(`The navigation shape changed - ${n}.${r} should now be ${n}.url.${r}`)}});return e}function le(){return()=>{}}async function Kt({env:n,hydrate:e,paths:r,target:i,trailing_slash:a}){it(r);const u=Nt({target:i,base:r.base,trailing_slash:a});ot({client:u}),e?await u._hydrate(e):u.goto(location.href,{replaceState:!0}),u._start_router()}export{Kt as start};
