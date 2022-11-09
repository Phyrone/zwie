import{S as N,i as F,s as V,k as W,a as R,q as we,l as M,m as E,h as g,c as x,r as be,n as A,C as y,b as k,D as v,E as te,F as ae,u as ve,G as We,H as G,I as Me,B as I,J as O,f as P,g as Ee,d as Ae,t as D,K as Pe,L as $e,w as ne,x as ie,y as se,z as re,M as De,N as Te,O as ze,P as Ie,o as Ce}from"../../chunks/index-46970648.js";import{a as Se,s as Le}from"../../chunks/messages-9145f738.js";import{S as j}from"../../chunks/sweetalert2.all-652a4ed8.js";import"../../chunks/theme-selector-7a652417.js";var ke=Object.defineProperty,q=(e,t)=>{for(var a in t)ke(e,a,{get:t[a],enumerable:!0})},Oe={};q(Oe,{convertFileSrc:()=>xe,invoke:()=>le,transformCallback:()=>C});function Re(){return window.crypto.getRandomValues(new Uint32Array(1))[0]}function C(e,t=!1){let a=Re(),s=`_${a}`;return Object.defineProperty(window,s,{value:n=>(t&&Reflect.deleteProperty(window,s),e==null?void 0:e(n)),writable:!1,configurable:!0}),a}async function le(e,t={}){return new Promise((a,s)=>{let n=C(i=>{a(i),Reflect.deleteProperty(window,`_${l}`)},!0),l=C(i=>{s(i),Reflect.deleteProperty(window,`_${n}`)},!0);window.__TAURI_IPC__({cmd:e,callback:n,error:l,...t})})}function xe(e,t="asset"){let a=encodeURIComponent(e);return navigator.userAgent.includes("Windows")?`https://${t}.localhost/${a}`:`${t}://localhost/${a}`}async function r(e){return le("tauri",e)}var Ue={};q(Ue,{TauriEvent:()=>de,emit:()=>Ve,listen:()=>Ne,once:()=>Fe});async function oe(e,t){return r({__tauriModule:"Event",message:{cmd:"unlisten",event:e,eventId:t}})}async function ce(e,t,a){await r({__tauriModule:"Event",message:{cmd:"emit",event:e,windowLabel:t,payload:a}})}async function B(e,t,a){return r({__tauriModule:"Event",message:{cmd:"listen",event:e,windowLabel:t,handler:C(a)}}).then(s=>async()=>oe(e,s))}async function ue(e,t,a){return B(e,t,s=>{a(s),oe(e,s.id).catch(()=>{})})}var de=(e=>(e.WINDOW_RESIZED="tauri://resize",e.WINDOW_MOVED="tauri://move",e.WINDOW_CLOSE_REQUESTED="tauri://close-requested",e.WINDOW_CREATED="tauri://window-created",e.WINDOW_DESTROYED="tauri://destroyed",e.WINDOW_FOCUS="tauri://focus",e.WINDOW_BLUR="tauri://blur",e.WINDOW_SCALE_FACTOR_CHANGED="tauri://scale-change",e.WINDOW_THEME_CHANGED="tauri://theme-changed",e.WINDOW_FILE_DROP="tauri://file-drop",e.WINDOW_FILE_DROP_HOVER="tauri://file-drop-hover",e.WINDOW_FILE_DROP_CANCELLED="tauri://file-drop-cancelled",e.MENU="tauri://menu",e.CHECK_UPDATE="tauri://update",e.UPDATE_AVAILABLE="tauri://update-available",e.INSTALL_UPDATE="tauri://update-install",e.STATUS_UPDATE="tauri://update-status",e.DOWNLOAD_PROGRESS="tauri://update-download-progress",e))(de||{});async function Ne(e,t){return B(e,null,t)}async function Fe(e,t){return ue(e,null,t)}async function Ve(e,t){return ce(e,void 0,t)}var qe={};q(qe,{CloseRequestedEvent:()=>fe,LogicalPosition:()=>he,LogicalSize:()=>me,PhysicalPosition:()=>L,PhysicalSize:()=>S,UserAttentionType:()=>_e,WebviewWindow:()=>$,WebviewWindowHandle:()=>ye,WindowManager:()=>ge,appWindow:()=>U,availableMonitors:()=>je,currentMonitor:()=>He,getAll:()=>pe,getCurrent:()=>Be,primaryMonitor:()=>Ge});var me=class{constructor(e,t){this.type="Logical",this.width=e,this.height=t}},S=class{constructor(e,t){this.type="Physical",this.width=e,this.height=t}toLogical(e){return new me(this.width/e,this.height/e)}},he=class{constructor(e,t){this.type="Logical",this.x=e,this.y=t}},L=class{constructor(e,t){this.type="Physical",this.x=e,this.y=t}toLogical(e){return new he(this.x/e,this.y/e)}},_e=(e=>(e[e.Critical=1]="Critical",e[e.Informational=2]="Informational",e))(_e||{});function Be(){return new $(window.__TAURI_METADATA__.__currentWindow.label,{skip:!0})}function pe(){return window.__TAURI_METADATA__.__windows.map(e=>new $(e.label,{skip:!0}))}var K=["tauri://created","tauri://error"],ye=class{constructor(e){this.label=e,this.listeners=Object.create(null)}async listen(e,t){return this._handleTauriEvent(e,t)?Promise.resolve(()=>{let a=this.listeners[e];a.splice(a.indexOf(t),1)}):B(e,this.label,t)}async once(e,t){return this._handleTauriEvent(e,t)?Promise.resolve(()=>{let a=this.listeners[e];a.splice(a.indexOf(t),1)}):ue(e,this.label,t)}async emit(e,t){if(K.includes(e)){for(let a of this.listeners[e]||[])a({event:e,id:-1,windowLabel:this.label,payload:t});return Promise.resolve()}return ce(e,this.label,t)}_handleTauriEvent(e,t){return K.includes(e)?(e in this.listeners?this.listeners[e].push(t):this.listeners[e]=[t],!0):!1}},ge=class extends ye{async scaleFactor(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"scaleFactor"}}}})}async innerPosition(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"innerPosition"}}}}).then(({x:e,y:t})=>new L(e,t))}async outerPosition(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"outerPosition"}}}}).then(({x:e,y:t})=>new L(e,t))}async innerSize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"innerSize"}}}}).then(({width:e,height:t})=>new S(e,t))}async outerSize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"outerSize"}}}}).then(({width:e,height:t})=>new S(e,t))}async isFullscreen(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"isFullscreen"}}}})}async isMaximized(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"isMaximized"}}}})}async isDecorated(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"isDecorated"}}}})}async isResizable(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"isResizable"}}}})}async isVisible(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"isVisible"}}}})}async theme(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"theme"}}}})}async center(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"center"}}}})}async requestUserAttention(e){let t=null;return e&&(e===1?t={type:"Critical"}:t={type:"Informational"}),r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"requestUserAttention",payload:t}}}})}async setResizable(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setResizable",payload:e}}}})}async setTitle(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setTitle",payload:e}}}})}async maximize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"maximize"}}}})}async unmaximize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"unmaximize"}}}})}async toggleMaximize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"toggleMaximize"}}}})}async minimize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"minimize"}}}})}async unminimize(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"unminimize"}}}})}async show(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"show"}}}})}async hide(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"hide"}}}})}async close(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"close"}}}})}async setDecorations(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setDecorations",payload:e}}}})}async setAlwaysOnTop(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setAlwaysOnTop",payload:e}}}})}async setSize(e){if(!e||e.type!=="Logical"&&e.type!=="Physical")throw new Error("the `size` argument must be either a LogicalSize or a PhysicalSize instance");return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setSize",payload:{type:e.type,data:{width:e.width,height:e.height}}}}}})}async setMinSize(e){if(e&&e.type!=="Logical"&&e.type!=="Physical")throw new Error("the `size` argument must be either a LogicalSize or a PhysicalSize instance");return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setMinSize",payload:e?{type:e.type,data:{width:e.width,height:e.height}}:null}}}})}async setMaxSize(e){if(e&&e.type!=="Logical"&&e.type!=="Physical")throw new Error("the `size` argument must be either a LogicalSize or a PhysicalSize instance");return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setMaxSize",payload:e?{type:e.type,data:{width:e.width,height:e.height}}:null}}}})}async setPosition(e){if(!e||e.type!=="Logical"&&e.type!=="Physical")throw new Error("the `position` argument must be either a LogicalPosition or a PhysicalPosition instance");return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setPosition",payload:{type:e.type,data:{x:e.x,y:e.y}}}}}})}async setFullscreen(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setFullscreen",payload:e}}}})}async setFocus(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setFocus"}}}})}async setIcon(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setIcon",payload:{icon:typeof e=="string"?e:Array.from(e)}}}}})}async setSkipTaskbar(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setSkipTaskbar",payload:e}}}})}async setCursorGrab(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setCursorGrab",payload:e}}}})}async setCursorVisible(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setCursorVisible",payload:e}}}})}async setCursorIcon(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setCursorIcon",payload:e}}}})}async setCursorPosition(e){if(!e||e.type!=="Logical"&&e.type!=="Physical")throw new Error("the `position` argument must be either a LogicalPosition or a PhysicalPosition instance");return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setCursorPosition",payload:{type:e.type,data:{x:e.x,y:e.y}}}}}})}async setIgnoreCursorEvents(e){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"setIgnoreCursorEvents",payload:e}}}})}async startDragging(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{label:this.label,cmd:{type:"startDragging"}}}})}async onResized(e){return this.listen("tauri://resize",e)}async onMoved(e){return this.listen("tauri://move",e)}async onCloseRequested(e){return this.listen("tauri://close-requested",t=>{let a=new fe(t);Promise.resolve(e(a)).then(()=>{if(!a.isPreventDefault())return this.close()})})}async onFocusChanged(e){let t=await this.listen("tauri://focus",s=>{e({...s,payload:!0})}),a=await this.listen("tauri://blur",s=>{e({...s,payload:!1})});return()=>{t(),a()}}async onScaleChanged(e){return this.listen("tauri://scale-change",e)}async onMenuClicked(e){return this.listen("tauri://menu",e)}async onFileDropEvent(e){let t=await this.listen("tauri://file-drop",n=>{e({...n,payload:{type:"drop",paths:n.payload}})}),a=await this.listen("tauri://file-drop-hover",n=>{e({...n,payload:{type:"hover",paths:n.payload}})}),s=await this.listen("tauri://file-drop-cancelled",n=>{e({...n,payload:{type:"cancel"}})});return()=>{t(),a(),s()}}async onThemeChanged(e){return this.listen("tauri://theme-changed",e)}},fe=class{constructor(e){this._preventDefault=!1,this.event=e.event,this.windowLabel=e.windowLabel,this.id=e.id}preventDefault(){this._preventDefault=!0}isPreventDefault(){return this._preventDefault}},$=class extends ge{constructor(e,t={}){super(e),t!=null&&t.skip||r({__tauriModule:"Window",message:{cmd:"createWebview",data:{options:{label:e,...t}}}}).then(async()=>this.emit("tauri://created")).catch(async a=>this.emit("tauri://error",a))}static getByLabel(e){return pe().some(t=>t.label===e)?new $(e,{skip:!0}):null}},U;"__TAURI_METADATA__"in window?U=new $(window.__TAURI_METADATA__.__currentWindow.label,{skip:!0}):(console.warn(`Could not find "window.__TAURI_METADATA__". The "appWindow" value will reference the "main" window label.
Note that this is not an issue if running this frontend on a browser instead of a Tauri window.`),U=new $("main",{skip:!0}));function H(e){return e===null?null:{name:e.name,scaleFactor:e.scaleFactor,position:new L(e.position.x,e.position.y),size:new S(e.size.width,e.size.height)}}async function He(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{cmd:{type:"currentMonitor"}}}}).then(H)}async function Ge(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{cmd:{type:"primaryMonitor"}}}}).then(H)}async function je(){return r({__tauriModule:"Window",message:{cmd:"manage",data:{cmd:{type:"availableMonitors"}}}}).then(e=>e.map(H))}function Ke(e){const t=e-1;return t*t*t+1}function Y(e,{delay:t=0,duration:a=400,easing:s=Ke,x:n=0,y:l=0,opacity:i=0}={}){const c=getComputedStyle(e),o=+c.opacity,_=c.transform==="none"?"":c.transform,h=o*(1-i);return{delay:t,duration:a,easing:s,css:(b,p)=>`
			transform: ${_} translate(${(1-b)*n}px, ${(1-b)*l}px);
			opacity: ${o-h*p}`}}function J(e){let t,a,s,n;return{c(){t=W("button"),a=W("i"),this.h()},l(l){t=M(l,"BUTTON",{class:!0});var i=E(t);a=M(i,"I",{class:!0}),E(a).forEach(g),i.forEach(g),this.h()},h(){A(a,"class","fa-solid fa-square-xmark"),A(t,"class","hover:text-neutral-focus active:text-neutral")},m(l,i){k(l,t,i),v(t,a),s||(n=te(t,"click",Me(ae(e[7]))),s=!0)},p:I,d(l){l&&g(t),s=!1,n()}}}function Ye(e){let t,a,s,n,l,i,c,o,_,h,b,p,m=e[6]&&J(e);return{c(){t=W("div"),a=W("i"),s=R(),n=W("div"),l=W("span"),i=we(e[5]),c=R(),o=W("div"),m&&m.c(),this.h()},l(u){t=M(u,"DIV",{class:!0});var d=E(t);a=M(d,"I",{class:!0}),E(a).forEach(g),s=x(d),n=M(d,"DIV",{class:!0});var T=E(n);l=M(T,"SPAN",{});var z=E(l);i=be(z,e[5]),z.forEach(g),T.forEach(g),c=x(d),o=M(d,"DIV",{class:!0});var f=E(o);m&&m.l(f),f.forEach(g),d.forEach(g),this.h()},h(){A(a,"class","select-none fa-solid"),y(a,"fa-info",e[4]==="info"),y(a,"fa-circle-exclamation",e[4]==="error"),y(a,"fa-triangle-exclamation",e[4]==="warning"),A(n,"class","select-none"),A(o,"class","flex-none"),A(t,"class","alert my-0.5 cursor-pointer"),y(t,"alert-info",e[4]==="info"),y(t,"alert-error",e[4]==="error"),y(t,"alert-warning",e[4]==="warning")},m(u,d){k(u,t,d),v(t,a),v(t,s),v(t,n),v(n,l),v(l,i),v(t,c),v(t,o),m&&m.m(o,null),h=!0,b||(p=te(t,"click",ae(e[8])),b=!0)},p(u,[d]){(!h||d&16)&&y(a,"fa-info",u[4]==="info"),(!h||d&16)&&y(a,"fa-circle-exclamation",u[4]==="error"),(!h||d&16)&&y(a,"fa-triangle-exclamation",u[4]==="warning"),(!h||d&32)&&ve(i,u[5]),u[6]?m?m.p(u,d):(m=J(u),m.c(),m.m(o,null)):m&&(m.d(1),m=null),(!h||d&16)&&y(t,"alert-info",u[4]==="info"),(!h||d&16)&&y(t,"alert-error",u[4]==="error"),(!h||d&16)&&y(t,"alert-warning",u[4]==="warning")},i(u){h||(We(()=>{_||(_=G(t,Y,{},!0)),_.run(1)}),h=!0)},o(u){_||(_=G(t,Y,{},!1)),_.run(0),h=!1},d(u){u&&g(t),m&&m.d(),u&&_&&_.end(),b=!1,p()}}}function Je(e,t,a){let s,n=I,l=()=>(n(),n=O(m,f=>a(4,s=f)),m),i,c=I,o=()=>(c(),c=O(u,f=>a(5,i=f)),u),_,h=I,b=()=>(h(),h=O(d,f=>a(6,_=f)),d);e.$$.on_destroy.push(()=>n()),e.$$.on_destroy.push(()=>c()),e.$$.on_destroy.push(()=>h());let{dispatchedAlert:p}=t,m,u,d;const T=()=>p.close(),z=()=>p.click();return e.$$set=f=>{"dispatchedAlert"in f&&a(0,p=f.dispatchedAlert)},e.$$.update=()=>{e.$$.dirty&1&&l(a(1,m=p.type)),e.$$.dirty&1&&o(a(2,u=p.text)),e.$$.dirty&1&&b(a(3,d=p.closeable))},[p,m,u,d,s,i,_,T,z]}class Qe extends N{constructor(t){super(),F(this,t,Je,Ye,V,{dispatchedAlert:0})}}function Q(e,t,a){const s=e.slice();return s[1]=t[a],s}function Z(e){let t,a;return t=new Qe({props:{dispatchedAlert:e[1]}}),{c(){ne(t.$$.fragment)},l(s){ie(t.$$.fragment,s)},m(s,n){se(t,s,n),a=!0},p(s,n){const l={};n&1&&(l.dispatchedAlert=s[1]),t.$set(l)},i(s){a||(P(t.$$.fragment,s),a=!0)},o(s){D(t.$$.fragment,s),a=!1},d(s){re(t,s)}}}function Ze(e){let t,a,s=e[0],n=[];for(let i=0;i<s.length;i+=1)n[i]=Z(Q(e,s,i));const l=i=>D(n[i],1,1,()=>{n[i]=null});return{c(){t=W("div");for(let i=0;i<n.length;i+=1)n[i].c();this.h()},l(i){t=M(i,"DIV",{class:!0});var c=E(t);for(let o=0;o<n.length;o+=1)n[o].l(c);c.forEach(g),this.h()},h(){A(t,"class","absolute top-0 right-0 h-full max-w-full flex flex-col overflow-x-auto z-40")},m(i,c){k(i,t,c);for(let o=0;o<n.length;o+=1)n[o].m(t,null);a=!0},p(i,[c]){if(c&1){s=i[0];let o;for(o=0;o<s.length;o+=1){const _=Q(i,s,o);n[o]?(n[o].p(_,c),P(n[o],1)):(n[o]=Z(_),n[o].c(),P(n[o],1),n[o].m(t,null))}for(Ee(),o=s.length;o<n.length;o+=1)l(o);Ae()}},i(i){if(!a){for(let c=0;c<s.length;c+=1)P(n[c]);a=!0}},o(i){n=n.filter(Boolean);for(let c=0;c<n.length;c+=1)D(n[c]);a=!1},d(i){i&&g(t),Pe(n,i)}}}function Xe(e,t,a){let s;return $e(e,Se,n=>a(0,s=n)),[s]}class et extends N{constructor(t){super(),F(this,t,Xe,Ze,V,{})}}function tt(){return"__TAURI__"in window?"tauri":"web"}const at=tt();let w;function X(){Le({text:"Update installed Click here to Restart",type:"info",showCloseButton:!1,onClick:async e=>{e.text.set("Installing Please wait..."),await nt()}})}async function nt(){await w.update(),w.installing?(console.log("SW still installing waiting for it to finish"),w.installing.addEventListener("statechange",ee)):await ee()}async function ee(){console.log("SW installed apply update to Application"),w.waiting?w.waiting.postMessage("APPLY_UPDATE"):window.location.reload()}async function it(){if(at==="tauri"){console.log("Service Worker not enabled in Tauri");return}if("serviceWorker"in navigator){navigator.serviceWorker.addEventListener("controllerchange",()=>{console.log("Controllerchange event -> reloading"),j.fire({title:"Restarting",text:"Please Wait"}),j.showLoading(),window.location.reload()});const e=await navigator.serviceWorker.register("/service-worker.js");e&&(w=e,w.waiting||w.installing?X():w.addEventListener("updatefound",X),setInterval(()=>e.update(),1e3*60*60))}else console.log("Service Worker not supported")}function st(e){let t,a,s;const n=e[1].default,l=De(n,e,e[0],null);return a=new et({}),{c(){l&&l.c(),t=R(),ne(a.$$.fragment)},l(i){l&&l.l(i),t=x(i),ie(a.$$.fragment,i)},m(i,c){l&&l.m(i,c),k(i,t,c),se(a,i,c),s=!0},p(i,[c]){l&&l.p&&(!s||c&1)&&Te(l,n,i,i[0],s?Ie(n,i[0],c,null):ze(i[0]),null)},i(i){s||(P(l,i),P(a.$$.fragment,i),s=!0)},o(i){D(l,i),D(a.$$.fragment,i),s=!1},d(i){l&&l.d(i),i&&g(t),re(a,i)}}}function rt(e,t,a){let{$$slots:s={},$$scope:n}=t;return Ce(it),e.$$set=l=>{"$$scope"in l&&a(0,n=l.$$scope)},[n,s]}class dt extends N{constructor(t){super(),F(this,t,rt,st,V,{})}}export{dt as default};
