function N(){}const it=t=>t;function rt(t,n){for(const e in n)t[e]=n[e];return t}function J(t){return t()}function F(){return Object.create(null)}function b(t){t.forEach(J)}function K(t){return typeof t=="function"}function Pt(t,n){return t!=t?n==n:t!==n||t&&typeof t=="object"||typeof t=="function"}function st(t){return Object.keys(t).length===0}function ot(t,...n){if(t==null)return N;const e=t.subscribe(...n);return e.unsubscribe?()=>e.unsubscribe():e}function Ot(t,n,e){t.$$.on_destroy.push(ot(n,e))}function Lt(t,n,e,r){if(t){const s=Q(t,n,e,r);return t[0](s)}}function Q(t,n,e,r){return t[1]&&r?rt(e.ctx.slice(),t[1](r(n))):e.ctx}function zt(t,n,e,r){if(t[2]&&r){const s=t[2](r(e));if(n.dirty===void 0)return s;if(typeof s=="object"){const u=[],i=Math.max(n.dirty.length,s.length);for(let l=0;l<i;l+=1)u[l]=n.dirty[l]|s[l];return u}return n.dirty|s}return n.dirty}function Bt(t,n,e,r,s,u){if(s){const i=Q(n,e,r,u);t.p(i,s)}}function Rt(t){if(t.ctx.length>32){const n=[],e=t.ctx.length/32;for(let r=0;r<e;r++)n[r]=-1;return n}return-1}const W=typeof window<"u";let lt=W?()=>window.performance.now():()=>Date.now(),R=W?t=>requestAnimationFrame(t):N;const x=new Set;function U(t){x.forEach(n=>{n.c(t)||(x.delete(n),n.f())}),x.size!==0&&R(U)}function ct(t){let n;return x.size===0&&R(U),{promise:new Promise(e=>{x.add(n={c:t,f:e})}),abort(){x.delete(n)}}}let P=!1;function ut(){P=!0}function at(){P=!1}function ft(t,n,e,r){for(;t<n;){const s=t+(n-t>>1);e(s)<=r?t=s+1:n=s}return t}function _t(t){if(t.hydrate_init)return;t.hydrate_init=!0;let n=t.childNodes;if(t.nodeName==="HEAD"){const o=[];for(let c=0;c<n.length;c++){const _=n[c];_.claim_order!==void 0&&o.push(_)}n=o}const e=new Int32Array(n.length+1),r=new Int32Array(n.length);e[0]=-1;let s=0;for(let o=0;o<n.length;o++){const c=n[o].claim_order,_=(s>0&&n[e[s]].claim_order<=c?s+1:ft(1,s,a=>n[e[a]].claim_order,c))-1;r[o]=e[_]+1;const f=_+1;e[f]=o,s=Math.max(f,s)}const u=[],i=[];let l=n.length-1;for(let o=e[s]+1;o!=0;o=r[o-1]){for(u.push(n[o-1]);l>=o;l--)i.push(n[l]);l--}for(;l>=0;l--)i.push(n[l]);u.reverse(),i.sort((o,c)=>o.claim_order-c.claim_order);for(let o=0,c=0;o<i.length;o++){for(;c<u.length&&i[o].claim_order>=u[c].claim_order;)c++;const _=c<u.length?u[c]:null;t.insertBefore(i[o],_)}}function dt(t,n){t.appendChild(n)}function ht(t){if(!t)return document;const n=t.getRootNode?t.getRootNode():t.ownerDocument;return n&&n.host?n:t.ownerDocument}function mt(t,n){return dt(t.head||t,n),n.sheet}function pt(t,n){if(P){for(_t(t),(t.actual_end_child===void 0||t.actual_end_child!==null&&t.actual_end_child.parentNode!==t)&&(t.actual_end_child=t.firstChild);t.actual_end_child!==null&&t.actual_end_child.claim_order===void 0;)t.actual_end_child=t.actual_end_child.nextSibling;n!==t.actual_end_child?(n.claim_order!==void 0||n.parentNode!==t)&&t.insertBefore(n,t.actual_end_child):t.actual_end_child=n.nextSibling}else(n.parentNode!==t||n.nextSibling!==null)&&t.appendChild(n)}function Tt(t,n,e){P&&!e?pt(t,n):(n.parentNode!==t||n.nextSibling!=e)&&t.insertBefore(n,e||null)}function V(t){t.parentNode.removeChild(t)}function qt(t,n){for(let e=0;e<t.length;e+=1)t[e]&&t[e].d(n)}function X(t){return document.createElement(t)}function T(t){return document.createTextNode(t)}function Ft(){return T(" ")}function Ht(){return T("")}function It(t,n,e,r){return t.addEventListener(n,e,r),()=>t.removeEventListener(n,e,r)}function Gt(t){return function(n){return n.preventDefault(),t.call(this,n)}}function Jt(t){return function(n){return n.stopPropagation(),t.call(this,n)}}function Kt(t,n,e){e==null?t.removeAttribute(n):t.getAttribute(n)!==e&&t.setAttribute(n,e)}function yt(t){return Array.from(t.childNodes)}function gt(t){t.claim_info===void 0&&(t.claim_info={last_index:0,total_claimed:0})}function Y(t,n,e,r,s=!1){gt(t);const u=(()=>{for(let i=t.claim_info.last_index;i<t.length;i++){const l=t[i];if(n(l)){const o=e(l);return o===void 0?t.splice(i,1):t[i]=o,s||(t.claim_info.last_index=i),l}}for(let i=t.claim_info.last_index-1;i>=0;i--){const l=t[i];if(n(l)){const o=e(l);return o===void 0?t.splice(i,1):t[i]=o,s?o===void 0&&t.claim_info.last_index--:t.claim_info.last_index=i,l}}return r()})();return u.claim_order=t.claim_info.total_claimed,t.claim_info.total_claimed+=1,u}function xt(t,n,e,r){return Y(t,s=>s.nodeName===n,s=>{const u=[];for(let i=0;i<s.attributes.length;i++){const l=s.attributes[i];e[l.name]||u.push(l.name)}u.forEach(i=>s.removeAttribute(i))},()=>r(n))}function Qt(t,n,e){return xt(t,n,e,X)}function bt(t,n){return Y(t,e=>e.nodeType===3,e=>{const r=""+n;if(e.data.startsWith(r)){if(e.data.length!==r.length)return e.splitText(r.length)}else e.data=r},()=>T(n),!0)}function Wt(t){return bt(t," ")}function Ut(t,n){n=""+n,t.wholeText!==n&&(t.data=n)}function Vt(t,n,e,r){e===null?t.style.removeProperty(n):t.style.setProperty(n,e,r?"important":"")}function Xt(t,n,e){t.classList[e?"add":"remove"](n)}function $t(t,n,{bubbles:e=!1,cancelable:r=!1}={}){const s=document.createEvent("CustomEvent");return s.initCustomEvent(t,e,r,n),s}const k=new Map;let M=0;function wt(t){let n=5381,e=t.length;for(;e--;)n=(n<<5)-n^t.charCodeAt(e);return n>>>0}function vt(t){const n={style_element:X("style"),rules:{}};return k.set(t,n),n}function H(t,n,e,r,s,u,i,l=0){const o=16.666/r;let c=`{
`;for(let m=0;m<=1;m+=o){const g=n+(e-n)*u(m);c+=m*100+`%{${i(g,1-g)}}
`}const _=c+`100% {${i(e,1-e)}}
}`,f=`__svelte_${wt(_)}_${l}`,a=ht(t),{style_element:h,rules:d}=k.get(a)||vt(a);if(!d[f]){const m=mt(a,h);d[f]=!0,m.insertRule(`@keyframes ${f} ${_}`,m.cssRules.length)}const y=t.style.animation||"";return t.style.animation=`${y?`${y}, `:""}${f} ${r}ms linear ${s}ms 1 both`,M+=1,f}function Et(t,n){const e=(t.style.animation||"").split(", "),r=e.filter(n?u=>u.indexOf(n)<0:u=>u.indexOf("__svelte")===-1),s=e.length-r.length;s&&(t.style.animation=r.join(", "),M-=s,M||Nt())}function Nt(){R(()=>{M||(k.forEach(t=>{const{style_element:n}=t;V(n)}),k.clear())})}let E;function v(t){E=t}function q(){if(!E)throw new Error("Function called outside component initialization");return E}function Yt(t){q().$$.on_mount.push(t)}function Zt(t){q().$$.after_update.push(t)}function tn(t){q().$$.on_destroy.push(t)}const w=[],I=[],S=[],G=[],Z=Promise.resolve();let B=!1;function tt(){B||(B=!0,Z.then(nt))}function nn(){return tt(),Z}function D(t){S.push(t)}const L=new Set;let C=0;function nt(){const t=E;do{for(;C<w.length;){const n=w[C];C++,v(n),At(n.$$)}for(v(null),w.length=0,C=0;I.length;)I.pop()();for(let n=0;n<S.length;n+=1){const e=S[n];L.has(e)||(L.add(e),e())}S.length=0}while(w.length);for(;G.length;)G.pop()();B=!1,L.clear(),v(t)}function At(t){if(t.fragment!==null){t.update(),b(t.before_update);const n=t.dirty;t.dirty=[-1],t.fragment&&t.fragment.p(t.ctx,n),t.after_update.forEach(D)}}let $;function Ct(){return $||($=Promise.resolve(),$.then(()=>{$=null})),$}function z(t,n,e){t.dispatchEvent($t(`${n?"intro":"outro"}${e}`))}const j=new Set;let p;function en(){p={r:0,c:[],p}}function rn(){p.r||b(p.c),p=p.p}function St(t,n){t&&t.i&&(j.delete(t),t.i(n))}function sn(t,n,e,r){if(t&&t.o){if(j.has(t))return;j.add(t),p.c.push(()=>{j.delete(t),r&&(e&&t.d(1),r())}),t.o(n)}else r&&r()}const jt={duration:0};function on(t,n,e,r){let s=n(t,e),u=r?0:1,i=null,l=null,o=null;function c(){o&&Et(t,o)}function _(a,h){const d=a.b-u;return h*=Math.abs(d),{a:u,b:a.b,d,duration:h,start:a.start,end:a.start+h,group:a.group}}function f(a){const{delay:h=0,duration:d=300,easing:y=it,tick:m=N,css:g}=s||jt,O={start:lt()+h,b:a};a||(O.group=p,p.r+=1),i||l?l=O:(g&&(c(),o=H(t,u,a,d,h,y,g)),a&&m(0,1),i=_(O,d),D(()=>z(t,a,"start")),ct(A=>{if(l&&A>l.start&&(i=_(l,d),l=null,z(t,i.b,"start"),g&&(c(),o=H(t,u,i.b,i.duration,0,y,s.css))),i){if(A>=i.end)m(u=i.b,1-u),z(t,i.b,"end"),l||(i.b?c():--i.group.r||b(i.group.c)),i=null;else if(A>=i.start){const et=A-i.start;u=i.a+i.d*y(et/i.duration),m(u,1-u)}}return!!(i||l)}))}return{run(a){K(s)?Ct().then(()=>{s=s(),f(a)}):f(a)},end(){c(),i=l=null}}}function ln(t){t&&t.c()}function cn(t,n){t&&t.l(n)}function kt(t,n,e,r){const{fragment:s,on_mount:u,on_destroy:i,after_update:l}=t.$$;s&&s.m(n,e),r||D(()=>{const o=u.map(J).filter(K);i?i.push(...o):b(o),t.$$.on_mount=[]}),l.forEach(D)}function Mt(t,n){const e=t.$$;e.fragment!==null&&(b(e.on_destroy),e.fragment&&e.fragment.d(n),e.on_destroy=e.fragment=null,e.ctx=[])}function Dt(t,n){t.$$.dirty[0]===-1&&(w.push(t),tt(),t.$$.dirty.fill(0)),t.$$.dirty[n/31|0]|=1<<n%31}function un(t,n,e,r,s,u,i,l=[-1]){const o=E;v(t);const c=t.$$={fragment:null,ctx:null,props:u,update:N,not_equal:s,bound:F(),on_mount:[],on_destroy:[],on_disconnect:[],before_update:[],after_update:[],context:new Map(n.context||(o?o.$$.context:[])),callbacks:F(),dirty:l,skip_bound:!1,root:n.target||o.$$.root};i&&i(c.root);let _=!1;if(c.ctx=e?e(t,n.props||{},(f,a,...h)=>{const d=h.length?h[0]:a;return c.ctx&&s(c.ctx[f],c.ctx[f]=d)&&(!c.skip_bound&&c.bound[f]&&c.bound[f](d),_&&Dt(t,f)),a}):[],c.update(),_=!0,b(c.before_update),c.fragment=r?r(c.ctx):!1,n.target){if(n.hydrate){ut();const f=yt(n.target);c.fragment&&c.fragment.l(f),f.forEach(V)}else c.fragment&&c.fragment.c();n.intro&&St(t.$$.fragment),kt(t,n.target,n.anchor,n.customElement),at(),nt()}v(o)}class an{$destroy(){Mt(this,1),this.$destroy=N}$on(n,e){const r=this.$$.callbacks[n]||(this.$$.callbacks[n]=[]);return r.push(e),()=>{const s=r.indexOf(e);s!==-1&&r.splice(s,1)}}$set(n){this.$$set&&!st(n)&&(this.$$.skip_bound=!0,this.$$set(n),this.$$.skip_bound=!1)}}export{N as A,Xt as B,pt as C,It as D,Gt as E,D as F,on as G,Jt as H,ot as I,qt as J,Ot as K,Lt as L,Bt as M,Rt as N,zt as O,tn as P,b as Q,an as S,Ft as a,Tt as b,Wt as c,rn as d,Ht as e,St as f,en as g,V as h,un as i,Zt as j,X as k,Qt as l,yt as m,Kt as n,Yt as o,Vt as p,T as q,bt as r,Pt as s,sn as t,Ut as u,ln as v,cn as w,kt as x,Mt as y,nn as z};
