import{S as F,i as G,s as J,k,q as b,a as x,l as m,m as p,r as A,h as l,c as E,n as y,b as n,C as h,D as W,A as H,Q as K}from"../../../chunks/index-6443e61f.js";import{s as d,S as B}from"../../../chunks/sweetalert2.all-4e992e51.js";function L(u){let s,o,e,_,T,C,i,O,N,c,S,v,a,U,w,f,I,q,Z;return{c(){s=k("h1"),o=b("Welcome to Zwie"),e=x(),_=k("h2"),T=b("dev-0.0.10"),C=x(),i=k("button"),O=b("Normal Alert"),N=x(),c=k("button"),S=b("Info Alert"),v=x(),a=k("button"),U=b("Warning Alert"),w=x(),f=k("button"),I=b("Error Alert"),this.h()},l(t){s=m(t,"H1",{});var r=p(s);o=A(r,"Welcome to Zwie"),r.forEach(l),e=E(t),_=m(t,"H2",{});var D=p(_);T=A(D,"dev-0.0.10"),D.forEach(l),C=E(t),i=m(t,"BUTTON",{class:!0});var P=p(i);O=A(P,"Normal Alert"),P.forEach(l),N=E(t),c=m(t,"BUTTON",{class:!0});var Q=p(c);S=A(Q,"Info Alert"),Q.forEach(l),v=E(t),a=m(t,"BUTTON",{class:!0});var j=p(a);U=A(j,"Warning Alert"),j.forEach(l),w=E(t),f=m(t,"BUTTON",{class:!0});var z=p(f);I=A(z,"Error Alert"),z.forEach(l),this.h()},h(){y(i,"class","btn"),y(c,"class","btn btn-info"),y(a,"class","btn btn-warning"),y(f,"class","btn btn-error")},m(t,r){n(t,s,r),h(s,o),n(t,e,r),n(t,_,r),h(_,T),n(t,C,r),n(t,i,r),h(i,O),n(t,N,r),n(t,c,r),h(c,S),n(t,v,r),n(t,a,r),h(a,U),n(t,w,r),n(t,f,r),h(f,I),q||(Z=[W(i,"click",u[1]),W(c,"click",u[2]),W(a,"click",u[3]),W(f,"click",u[4])],q=!0)},p:H,i:H,o:H,d(t){t&&l(s),t&&l(e),t&&l(_),t&&l(C),t&&l(i),t&&l(N),t&&l(c),t&&l(v),t&&l(a),t&&l(w),t&&l(f),q=!1,K(Z)}}}function M(u,s,o){let e=1;return[e,()=>d({text:"Normal Alert "+o(0,e++,e),onClick:()=>B.fire({text:"Click"})}),()=>d({text:"Info Alert "+o(0,e++,e),type:"info",onClick:()=>B.fire({text:"Click",icon:"info"})}),()=>d({text:"Warning Alert "+o(0,e++,e),type:"warning",onClick:()=>B.fire({text:"Click",icon:"warning"})}),()=>d({text:"Error Alert "+o(0,e++,e),type:"error",onClick:()=>B.fire({text:"Click",icon:"error"})})]}class X extends F{constructor(s){super(),G(this,s,M,L,J,{})}}export{X as default};
