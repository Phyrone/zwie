import{w as u}from"./index-1e50f43e.js";let _="",f="",b="";function E(e){f=e.base,_=e.assets||f}function R(e){b=e}const k="sveltekit:scroll",A="sveltekit:index",d={tap:1,hover:2,viewport:3,eager:4,off:-1};function I(e){let t=e.baseURI;if(!t){const n=e.getElementsByTagName("base");t=n.length?n[0].href:e.URL}return t}function y(){return{x:pageXOffset,y:pageYOffset}}function l(e,t){return e.getAttribute(`data-sveltekit-${t}`)}const p={...d,"":d.hover};function g(e){let t=e.assignedSlot??e.parentNode;return(t==null?void 0:t.nodeType)===11&&(t=t.host),t}function x(e,t){for(;e&&e!==t;){if(e.nodeName.toUpperCase()==="A"&&e.hasAttribute("href"))return e;e=g(e)}}function S(e,t){let n;try{n=new URL(e instanceof SVGAElement?e.href.baseVal:e.href,document.baseURI)}catch{}const a=e instanceof SVGAElement?e.target.baseVal:e.target,o=!n||!!a||m(n,t)||(e.getAttribute("rel")||"").split(/\s+/).includes("external")||e.hasAttribute("download");return{url:n,external:o,target:a}}function T(e){let t=null,n=null,a=null,o=null,s=e;for(;s&&s!==document.documentElement;)n===null&&(n=l(s,"preload-code")),a===null&&(a=l(s,"preload-data")),t===null&&(t=l(s,"noscroll")),o===null&&(o=l(s,"reload")),s=g(s);return{preload_code:p[n??"off"],preload_data:p[a??"off"],noscroll:t==="off"?!1:t===""?!0:null,reload:o==="off"?!1:o===""?!0:null}}function h(e){const t=u(e);let n=!0;function a(){n=!0,t.update(r=>r)}function o(r){n=!1,t.set(r)}function s(r){let i;return t.subscribe(c=>{(i===void 0||n&&c!==i)&&r(i=c)})}return{notify:a,set:o,subscribe:s}}function v(){const{set:e,subscribe:t}=u(!1);let n;async function a(){clearTimeout(n);const o=await fetch(`${_}/_app/version.json`,{headers:{pragma:"no-cache","cache-control":"no-cache"}});if(o.ok){const r=(await o.json()).version!==b;return r&&(e(!0),clearTimeout(n)),r}else throw new Error(`Version check failed: ${o.status}`)}return{subscribe:t,check:a}}function m(e,t){return e.origin!==location.origin||!e.pathname.startsWith(t)}function U(e){e.client}const L={url:h({}),page:h({}),navigating:u(null),updated:v()};export{A as I,d as P,k as S,S as a,T as b,y as c,E as d,U as e,x as f,I as g,R as h,m as i,L as s};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoic2luZ2xldG9ucy0zNmRjYTk1MS5qcyIsInNvdXJjZXMiOlsiLi4vLi4vLi4vLi4vLi4vLi4vbm9kZV9tb2R1bGVzL0BzdmVsdGVqcy9raXQvc3JjL3J1bnRpbWUvc2hhcmVkLmpzIiwiLi4vLi4vLi4vLi4vLi4vLi4vbm9kZV9tb2R1bGVzL0BzdmVsdGVqcy9raXQvc3JjL3J1bnRpbWUvY2xpZW50L2NvbnN0YW50cy5qcyIsIi4uLy4uLy4uLy4uLy4uLy4uL25vZGVfbW9kdWxlcy9Ac3ZlbHRlanMva2l0L3NyYy9ydW50aW1lL2NsaWVudC91dGlscy5qcyIsIi4uLy4uLy4uLy4uLy4uLy4uL25vZGVfbW9kdWxlcy9Ac3ZlbHRlanMva2l0L3NyYy9ydW50aW1lL2NsaWVudC9zaW5nbGV0b25zLmpzIl0sInNvdXJjZXNDb250ZW50IjpbImV4cG9ydCBsZXQgYXNzZXRzID0gJyc7XG5leHBvcnQgbGV0IGJhc2UgPSAnJztcbmV4cG9ydCBsZXQgYnVpbGRpbmcgPSBmYWxzZTtcbmV4cG9ydCBsZXQgdmVyc2lvbiA9ICcnO1xuXG4vKiogQHR5cGUge1JlY29yZDxzdHJpbmcsIHN0cmluZz59ICovXG5leHBvcnQgbGV0IHByaXZhdGVfZW52ID0ge307XG5cbi8qKiBAdHlwZSB7UmVjb3JkPHN0cmluZywgc3RyaW5nPn0gKi9cbmV4cG9ydCBsZXQgcHVibGljX2VudiA9IHt9O1xuXG4vKiogQHBhcmFtIHtzdHJpbmd9IHN0YWNrICovXG5leHBvcnQgbGV0IGZpeF9zdGFja190cmFjZSA9IChzdGFjaykgPT4gc3RhY2s7XG5cbi8qKiBAcGFyYW0ge3sgYmFzZTogc3RyaW5nLCBhc3NldHM6IHN0cmluZyB9fSBwYXRocyAqL1xuZXhwb3J0IGZ1bmN0aW9uIHNldF9wYXRocyhwYXRocykge1xuXHRiYXNlID0gcGF0aHMuYmFzZTtcblx0YXNzZXRzID0gcGF0aHMuYXNzZXRzIHx8IGJhc2U7XG59XG5cbi8qKiBAcGFyYW0ge2Jvb2xlYW59IHZhbHVlICovXG5leHBvcnQgZnVuY3Rpb24gc2V0X2J1aWxkaW5nKHZhbHVlKSB7XG5cdGJ1aWxkaW5nID0gdmFsdWU7XG59XG5cbi8qKiBAdHlwZSB7KGVudmlyb25tZW50OiBSZWNvcmQ8c3RyaW5nLCBzdHJpbmc+KSA9PiB2b2lkfSAqL1xuZXhwb3J0IGZ1bmN0aW9uIHNldF9wcml2YXRlX2VudihlbnZpcm9ubWVudCkge1xuXHRwcml2YXRlX2VudiA9IGVudmlyb25tZW50O1xufVxuXG4vKiogQHR5cGUgeyhlbnZpcm9ubWVudDogUmVjb3JkPHN0cmluZywgc3RyaW5nPikgPT4gdm9pZH0gKi9cbmV4cG9ydCBmdW5jdGlvbiBzZXRfcHVibGljX2VudihlbnZpcm9ubWVudCkge1xuXHRwdWJsaWNfZW52ID0gZW52aXJvbm1lbnQ7XG59XG5cbi8qKiBAcGFyYW0ge3N0cmluZ30gdmFsdWUgKi9cbmV4cG9ydCBmdW5jdGlvbiBzZXRfdmVyc2lvbih2YWx1ZSkge1xuXHR2ZXJzaW9uID0gdmFsdWU7XG59XG5cbi8qKiBAcGFyYW0geyhzdGFjazogc3RyaW5nKSA9PiBzdHJpbmd9IHZhbHVlICovXG5leHBvcnQgZnVuY3Rpb24gc2V0X2ZpeF9zdGFja190cmFjZSh2YWx1ZSkge1xuXHRmaXhfc3RhY2tfdHJhY2UgPSB2YWx1ZTtcbn1cbiIsImV4cG9ydCBjb25zdCBTQ1JPTExfS0VZID0gJ3N2ZWx0ZWtpdDpzY3JvbGwnO1xuZXhwb3J0IGNvbnN0IElOREVYX0tFWSA9ICdzdmVsdGVraXQ6aW5kZXgnO1xuXG5leHBvcnQgY29uc3QgUFJFTE9BRF9QUklPUklUSUVTID0gLyoqIEB0eXBlIHtjb25zdH0gKi8gKHtcblx0dGFwOiAxLFxuXHRob3ZlcjogMixcblx0dmlld3BvcnQ6IDMsXG5cdGVhZ2VyOiA0LFxuXHRvZmY6IC0xXG59KTtcbiIsImltcG9ydCB7IEJST1dTRVIsIERFViB9IGZyb20gJ2VzbS1lbnYnO1xuaW1wb3J0IHsgd3JpdGFibGUgfSBmcm9tICdzdmVsdGUvc3RvcmUnO1xuaW1wb3J0IHsgYXNzZXRzLCB2ZXJzaW9uIH0gZnJvbSAnLi4vc2hhcmVkLmpzJztcbmltcG9ydCB7IFBSRUxPQURfUFJJT1JJVElFUyB9IGZyb20gJy4vY29uc3RhbnRzLmpzJztcblxuLyogZ2xvYmFsIF9fU1ZFTFRFS0lUX0FQUF9WRVJTSU9OX0ZJTEVfXywgX19TVkVMVEVLSVRfQVBQX1ZFUlNJT05fUE9MTF9JTlRFUlZBTF9fICovXG5cbi8qKiBAcGFyYW0ge0hUTUxEb2N1bWVudH0gZG9jICovXG5leHBvcnQgZnVuY3Rpb24gZ2V0X2Jhc2VfdXJpKGRvYykge1xuXHRsZXQgYmFzZVVSSSA9IGRvYy5iYXNlVVJJO1xuXG5cdGlmICghYmFzZVVSSSkge1xuXHRcdGNvbnN0IGJhc2VUYWdzID0gZG9jLmdldEVsZW1lbnRzQnlUYWdOYW1lKCdiYXNlJyk7XG5cdFx0YmFzZVVSSSA9IGJhc2VUYWdzLmxlbmd0aCA/IGJhc2VUYWdzWzBdLmhyZWYgOiBkb2MuVVJMO1xuXHR9XG5cblx0cmV0dXJuIGJhc2VVUkk7XG59XG5cbmV4cG9ydCBmdW5jdGlvbiBzY3JvbGxfc3RhdGUoKSB7XG5cdHJldHVybiB7XG5cdFx0eDogcGFnZVhPZmZzZXQsXG5cdFx0eTogcGFnZVlPZmZzZXRcblx0fTtcbn1cblxuY29uc3Qgd2FybmVkID0gbmV3IFdlYWtTZXQoKTtcblxuLyoqIEB0eXBlZGVmIHtrZXlvZiB0eXBlb2YgdmFsaWRfbGlua19vcHRpb25zfSBMaW5rT3B0aW9uTmFtZSAqL1xuXG5jb25zdCB2YWxpZF9saW5rX29wdGlvbnMgPSAvKiogQHR5cGUge2NvbnN0fSAqLyAoe1xuXHQncHJlbG9hZC1jb2RlJzogWycnLCAnb2ZmJywgJ3RhcCcsICdob3ZlcicsICd2aWV3cG9ydCcsICdlYWdlciddLFxuXHQncHJlbG9hZC1kYXRhJzogWycnLCAnb2ZmJywgJ3RhcCcsICdob3ZlciddLFxuXHRub3Njcm9sbDogWycnLCAnb2ZmJ10sXG5cdHJlbG9hZDogWycnLCAnb2ZmJ11cbn0pO1xuXG4vKipcbiAqIEB0ZW1wbGF0ZSB7TGlua09wdGlvbk5hbWV9IFRcbiAqIEB0eXBlZGVmIHt0eXBlb2YgdmFsaWRfbGlua19vcHRpb25zW1RdW251bWJlcl19IFZhbGlkTGlua09wdGlvbnNcbiAqL1xuXG4vKipcbiAqIEB0ZW1wbGF0ZSB7TGlua09wdGlvbk5hbWV9IFRcbiAqIEBwYXJhbSB7RWxlbWVudH0gZWxlbWVudFxuICogQHBhcmFtIHtUfSBuYW1lXG4gKi9cbmZ1bmN0aW9uIGxpbmtfb3B0aW9uKGVsZW1lbnQsIG5hbWUpIHtcblx0Y29uc3QgdmFsdWUgPSAvKiogQHR5cGUge1ZhbGlkTGlua09wdGlvbnM8VD4gfCBudWxsfSAqLyAoXG5cdFx0ZWxlbWVudC5nZXRBdHRyaWJ1dGUoYGRhdGEtc3ZlbHRla2l0LSR7bmFtZX1gKVxuXHQpO1xuXG5cdGlmIChERVYpIHtcblx0XHR2YWxpZGF0ZV9saW5rX29wdGlvbihlbGVtZW50LCBuYW1lLCB2YWx1ZSk7XG5cdH1cblxuXHRyZXR1cm4gdmFsdWU7XG59XG5cbi8qKlxuICogQHRlbXBsYXRlIHtMaW5rT3B0aW9uTmFtZX0gVFxuICogQHRlbXBsYXRlIHtWYWxpZExpbmtPcHRpb25zPFQ+IHwgbnVsbH0gVVxuICogQHBhcmFtIHtFbGVtZW50fSBlbGVtZW50XG4gKiBAcGFyYW0ge1R9IG5hbWVcbiAqIEBwYXJhbSB7VX0gdmFsdWVcbiAqL1xuZnVuY3Rpb24gdmFsaWRhdGVfbGlua19vcHRpb24oZWxlbWVudCwgbmFtZSwgdmFsdWUpIHtcblx0aWYgKHZhbHVlID09PSBudWxsKSByZXR1cm47XG5cblx0Ly8gQHRzLWV4cGVjdC1lcnJvciAtIGluY2x1ZGVzIGlzIGR1bWJcblx0aWYgKCF3YXJuZWQuaGFzKGVsZW1lbnQpICYmICF2YWxpZF9saW5rX29wdGlvbnNbbmFtZV0uaW5jbHVkZXModmFsdWUpKSB7XG5cdFx0Y29uc29sZS5lcnJvcihcblx0XHRcdGBVbmV4cGVjdGVkIHZhbHVlIGZvciAke25hbWV9IOKAlCBzaG91bGQgYmUgb25lIG9mICR7dmFsaWRfbGlua19vcHRpb25zW25hbWVdXG5cdFx0XHRcdC5tYXAoKG9wdGlvbikgPT4gSlNPTi5zdHJpbmdpZnkob3B0aW9uKSlcblx0XHRcdFx0LmpvaW4oJywgJyl9YCxcblx0XHRcdGVsZW1lbnRcblx0XHQpO1xuXG5cdFx0d2FybmVkLmFkZChlbGVtZW50KTtcblx0fVxufVxuXG5jb25zdCBsZXZlbHMgPSB7XG5cdC4uLlBSRUxPQURfUFJJT1JJVElFUyxcblx0Jyc6IFBSRUxPQURfUFJJT1JJVElFUy5ob3ZlclxufTtcblxuLyoqXG4gKiBAcGFyYW0ge0VsZW1lbnR9IGVsZW1lbnRcbiAqIEByZXR1cm5zIHtFbGVtZW50IHwgbnVsbH1cbiAqL1xuZnVuY3Rpb24gcGFyZW50X2VsZW1lbnQoZWxlbWVudCkge1xuXHRsZXQgcGFyZW50ID0gZWxlbWVudC5hc3NpZ25lZFNsb3QgPz8gZWxlbWVudC5wYXJlbnROb2RlO1xuXG5cdC8vIEB0cy1leHBlY3QtZXJyb3IgaGFuZGxlIHNoYWRvdyByb290c1xuXHRpZiAocGFyZW50Py5ub2RlVHlwZSA9PT0gMTEpIHBhcmVudCA9IHBhcmVudC5ob3N0O1xuXG5cdHJldHVybiAvKiogQHR5cGUge0VsZW1lbnR9ICovIChwYXJlbnQpO1xufVxuXG4vKipcbiAqIEBwYXJhbSB7RWxlbWVudH0gZWxlbWVudFxuICogQHBhcmFtIHtFbGVtZW50fSB0YXJnZXRcbiAqL1xuZXhwb3J0IGZ1bmN0aW9uIGZpbmRfYW5jaG9yKGVsZW1lbnQsIHRhcmdldCkge1xuXHR3aGlsZSAoZWxlbWVudCAmJiBlbGVtZW50ICE9PSB0YXJnZXQpIHtcblx0XHRpZiAoZWxlbWVudC5ub2RlTmFtZS50b1VwcGVyQ2FzZSgpID09PSAnQScgJiYgZWxlbWVudC5oYXNBdHRyaWJ1dGUoJ2hyZWYnKSkge1xuXHRcdFx0cmV0dXJuIC8qKiBAdHlwZSB7SFRNTEFuY2hvckVsZW1lbnQgfCBTVkdBRWxlbWVudH0gKi8gKGVsZW1lbnQpO1xuXHRcdH1cblxuXHRcdGVsZW1lbnQgPSAvKiogQHR5cGUge0VsZW1lbnR9ICovIChwYXJlbnRfZWxlbWVudChlbGVtZW50KSk7XG5cdH1cbn1cblxuLyoqXG4gKiBAcGFyYW0ge0hUTUxBbmNob3JFbGVtZW50IHwgU1ZHQUVsZW1lbnR9IGFcbiAqIEBwYXJhbSB7c3RyaW5nfSBiYXNlXG4gKi9cbmV4cG9ydCBmdW5jdGlvbiBnZXRfbGlua19pbmZvKGEsIGJhc2UpIHtcblx0LyoqIEB0eXBlIHtVUkwgfCB1bmRlZmluZWR9ICovXG5cdGxldCB1cmw7XG5cblx0dHJ5IHtcblx0XHR1cmwgPSBuZXcgVVJMKGEgaW5zdGFuY2VvZiBTVkdBRWxlbWVudCA/IGEuaHJlZi5iYXNlVmFsIDogYS5ocmVmLCBkb2N1bWVudC5iYXNlVVJJKTtcblx0fSBjYXRjaCB7fVxuXG5cdGNvbnN0IHRhcmdldCA9IGEgaW5zdGFuY2VvZiBTVkdBRWxlbWVudCA/IGEudGFyZ2V0LmJhc2VWYWwgOiBhLnRhcmdldDtcblxuXHRjb25zdCBleHRlcm5hbCA9XG5cdFx0IXVybCB8fFxuXHRcdCEhdGFyZ2V0IHx8XG5cdFx0aXNfZXh0ZXJuYWxfdXJsKHVybCwgYmFzZSkgfHxcblx0XHQoYS5nZXRBdHRyaWJ1dGUoJ3JlbCcpIHx8ICcnKS5zcGxpdCgvXFxzKy8pLmluY2x1ZGVzKCdleHRlcm5hbCcpIHx8XG5cdFx0YS5oYXNBdHRyaWJ1dGUoJ2Rvd25sb2FkJyk7XG5cblx0cmV0dXJuIHsgdXJsLCBleHRlcm5hbCwgdGFyZ2V0IH07XG59XG5cbi8qKlxuICogQHBhcmFtIHtIVE1MRm9ybUVsZW1lbnQgfCBIVE1MQW5jaG9yRWxlbWVudCB8IFNWR0FFbGVtZW50fSBlbGVtZW50XG4gKi9cbmV4cG9ydCBmdW5jdGlvbiBnZXRfcm91dGVyX29wdGlvbnMoZWxlbWVudCkge1xuXHQvKiogQHR5cGUge1ZhbGlkTGlua09wdGlvbnM8J25vc2Nyb2xsJz4gfCBudWxsfSAqL1xuXHRsZXQgbm9zY3JvbGwgPSBudWxsO1xuXG5cdC8qKiBAdHlwZSB7VmFsaWRMaW5rT3B0aW9uczwncHJlbG9hZC1jb2RlJz4gfCBudWxsfSAqL1xuXHRsZXQgcHJlbG9hZF9jb2RlID0gbnVsbDtcblxuXHQvKiogQHR5cGUge1ZhbGlkTGlua09wdGlvbnM8J3ByZWxvYWQtZGF0YSc+IHwgbnVsbH0gKi9cblx0bGV0IHByZWxvYWRfZGF0YSA9IG51bGw7XG5cblx0LyoqIEB0eXBlIHtWYWxpZExpbmtPcHRpb25zPCdyZWxvYWQnPiB8IG51bGx9ICovXG5cdGxldCByZWxvYWQgPSBudWxsO1xuXG5cdC8qKiBAdHlwZSB7RWxlbWVudH0gKi9cblx0bGV0IGVsID0gZWxlbWVudDtcblxuXHR3aGlsZSAoZWwgJiYgZWwgIT09IGRvY3VtZW50LmRvY3VtZW50RWxlbWVudCkge1xuXHRcdGlmIChwcmVsb2FkX2NvZGUgPT09IG51bGwpIHByZWxvYWRfY29kZSA9IGxpbmtfb3B0aW9uKGVsLCAncHJlbG9hZC1jb2RlJyk7XG5cdFx0aWYgKHByZWxvYWRfZGF0YSA9PT0gbnVsbCkgcHJlbG9hZF9kYXRhID0gbGlua19vcHRpb24oZWwsICdwcmVsb2FkLWRhdGEnKTtcblx0XHRpZiAobm9zY3JvbGwgPT09IG51bGwpIG5vc2Nyb2xsID0gbGlua19vcHRpb24oZWwsICdub3Njcm9sbCcpO1xuXHRcdGlmIChyZWxvYWQgPT09IG51bGwpIHJlbG9hZCA9IGxpbmtfb3B0aW9uKGVsLCAncmVsb2FkJyk7XG5cblx0XHRlbCA9IC8qKiBAdHlwZSB7RWxlbWVudH0gKi8gKHBhcmVudF9lbGVtZW50KGVsKSk7XG5cdH1cblxuXHRyZXR1cm4ge1xuXHRcdHByZWxvYWRfY29kZTogbGV2ZWxzW3ByZWxvYWRfY29kZSA/PyAnb2ZmJ10sXG5cdFx0cHJlbG9hZF9kYXRhOiBsZXZlbHNbcHJlbG9hZF9kYXRhID8/ICdvZmYnXSxcblx0XHRub3Njcm9sbDogbm9zY3JvbGwgPT09ICdvZmYnID8gZmFsc2UgOiBub3Njcm9sbCA9PT0gJycgPyB0cnVlIDogbnVsbCxcblx0XHRyZWxvYWQ6IHJlbG9hZCA9PT0gJ29mZicgPyBmYWxzZSA6IHJlbG9hZCA9PT0gJycgPyB0cnVlIDogbnVsbFxuXHR9O1xufVxuXG4vKiogQHBhcmFtIHthbnl9IHZhbHVlICovXG5leHBvcnQgZnVuY3Rpb24gbm90aWZpYWJsZV9zdG9yZSh2YWx1ZSkge1xuXHRjb25zdCBzdG9yZSA9IHdyaXRhYmxlKHZhbHVlKTtcblx0bGV0IHJlYWR5ID0gdHJ1ZTtcblxuXHRmdW5jdGlvbiBub3RpZnkoKSB7XG5cdFx0cmVhZHkgPSB0cnVlO1xuXHRcdHN0b3JlLnVwZGF0ZSgodmFsKSA9PiB2YWwpO1xuXHR9XG5cblx0LyoqIEBwYXJhbSB7YW55fSBuZXdfdmFsdWUgKi9cblx0ZnVuY3Rpb24gc2V0KG5ld192YWx1ZSkge1xuXHRcdHJlYWR5ID0gZmFsc2U7XG5cdFx0c3RvcmUuc2V0KG5ld192YWx1ZSk7XG5cdH1cblxuXHQvKiogQHBhcmFtIHsodmFsdWU6IGFueSkgPT4gdm9pZH0gcnVuICovXG5cdGZ1bmN0aW9uIHN1YnNjcmliZShydW4pIHtcblx0XHQvKiogQHR5cGUge2FueX0gKi9cblx0XHRsZXQgb2xkX3ZhbHVlO1xuXHRcdHJldHVybiBzdG9yZS5zdWJzY3JpYmUoKG5ld192YWx1ZSkgPT4ge1xuXHRcdFx0aWYgKG9sZF92YWx1ZSA9PT0gdW5kZWZpbmVkIHx8IChyZWFkeSAmJiBuZXdfdmFsdWUgIT09IG9sZF92YWx1ZSkpIHtcblx0XHRcdFx0cnVuKChvbGRfdmFsdWUgPSBuZXdfdmFsdWUpKTtcblx0XHRcdH1cblx0XHR9KTtcblx0fVxuXG5cdHJldHVybiB7IG5vdGlmeSwgc2V0LCBzdWJzY3JpYmUgfTtcbn1cblxuZXhwb3J0IGZ1bmN0aW9uIGNyZWF0ZV91cGRhdGVkX3N0b3JlKCkge1xuXHRjb25zdCB7IHNldCwgc3Vic2NyaWJlIH0gPSB3cml0YWJsZShmYWxzZSk7XG5cblx0Y29uc3QgaW50ZXJ2YWwgPSBfX1NWRUxURUtJVF9BUFBfVkVSU0lPTl9QT0xMX0lOVEVSVkFMX187XG5cblx0LyoqIEB0eXBlIHtOb2RlSlMuVGltZW91dH0gKi9cblx0bGV0IHRpbWVvdXQ7XG5cblx0LyoqIEB0eXBlIHsoKSA9PiBQcm9taXNlPGJvb2xlYW4+fSAqL1xuXHRhc3luYyBmdW5jdGlvbiBjaGVjaygpIHtcblx0XHRpZiAoREVWIHx8ICFCUk9XU0VSKSByZXR1cm4gZmFsc2U7XG5cblx0XHRjbGVhclRpbWVvdXQodGltZW91dCk7XG5cblx0XHRpZiAoaW50ZXJ2YWwpIHRpbWVvdXQgPSBzZXRUaW1lb3V0KGNoZWNrLCBpbnRlcnZhbCk7XG5cblx0XHRjb25zdCByZXMgPSBhd2FpdCBmZXRjaChgJHthc3NldHN9LyR7X19TVkVMVEVLSVRfQVBQX1ZFUlNJT05fRklMRV9ffWAsIHtcblx0XHRcdGhlYWRlcnM6IHtcblx0XHRcdFx0cHJhZ21hOiAnbm8tY2FjaGUnLFxuXHRcdFx0XHQnY2FjaGUtY29udHJvbCc6ICduby1jYWNoZSdcblx0XHRcdH1cblx0XHR9KTtcblxuXHRcdGlmIChyZXMub2spIHtcblx0XHRcdGNvbnN0IGRhdGEgPSBhd2FpdCByZXMuanNvbigpO1xuXHRcdFx0Y29uc3QgdXBkYXRlZCA9IGRhdGEudmVyc2lvbiAhPT0gdmVyc2lvbjtcblxuXHRcdFx0aWYgKHVwZGF0ZWQpIHtcblx0XHRcdFx0c2V0KHRydWUpO1xuXHRcdFx0XHRjbGVhclRpbWVvdXQodGltZW91dCk7XG5cdFx0XHR9XG5cblx0XHRcdHJldHVybiB1cGRhdGVkO1xuXHRcdH0gZWxzZSB7XG5cdFx0XHR0aHJvdyBuZXcgRXJyb3IoYFZlcnNpb24gY2hlY2sgZmFpbGVkOiAke3Jlcy5zdGF0dXN9YCk7XG5cdFx0fVxuXHR9XG5cblx0aWYgKGludGVydmFsKSB0aW1lb3V0ID0gc2V0VGltZW91dChjaGVjaywgaW50ZXJ2YWwpO1xuXG5cdHJldHVybiB7XG5cdFx0c3Vic2NyaWJlLFxuXHRcdGNoZWNrXG5cdH07XG59XG5cbi8qKlxuICogQHBhcmFtIHtVUkx9IHVybFxuICogQHBhcmFtIHtzdHJpbmd9IGJhc2VcbiAqL1xuZXhwb3J0IGZ1bmN0aW9uIGlzX2V4dGVybmFsX3VybCh1cmwsIGJhc2UpIHtcblx0cmV0dXJuIHVybC5vcmlnaW4gIT09IGxvY2F0aW9uLm9yaWdpbiB8fCAhdXJsLnBhdGhuYW1lLnN0YXJ0c1dpdGgoYmFzZSk7XG59XG4iLCJpbXBvcnQgeyB3cml0YWJsZSB9IGZyb20gJ3N2ZWx0ZS9zdG9yZSc7XG5pbXBvcnQgeyBjcmVhdGVfdXBkYXRlZF9zdG9yZSwgbm90aWZpYWJsZV9zdG9yZSB9IGZyb20gJy4vdXRpbHMuanMnO1xuXG4vKiogQHR5cGUge2ltcG9ydCgnLi90eXBlcycpLkNsaWVudH0gKi9cbmV4cG9ydCBsZXQgY2xpZW50O1xuXG4vKipcbiAqIEBwYXJhbSB7e1xuICogICBjbGllbnQ6IGltcG9ydCgnLi90eXBlcycpLkNsaWVudDtcbiAqIH19IG9wdHNcbiAqL1xuZXhwb3J0IGZ1bmN0aW9uIGluaXQob3B0cykge1xuXHRjbGllbnQgPSBvcHRzLmNsaWVudDtcbn1cblxuZXhwb3J0IGNvbnN0IHN0b3JlcyA9IHtcblx0dXJsOiBub3RpZmlhYmxlX3N0b3JlKHt9KSxcblx0cGFnZTogbm90aWZpYWJsZV9zdG9yZSh7fSksXG5cdG5hdmlnYXRpbmc6IHdyaXRhYmxlKC8qKiBAdHlwZSB7aW1wb3J0KCd0eXBlcycpLk5hdmlnYXRpb24gfCBudWxsfSAqLyAobnVsbCkpLFxuXHR1cGRhdGVkOiBjcmVhdGVfdXBkYXRlZF9zdG9yZSgpXG59O1xuIl0sIm5hbWVzIjpbImFzc2V0cyIsImJhc2UiLCJ2ZXJzaW9uIiwic2V0X3BhdGhzIiwicGF0aHMiLCJzZXRfdmVyc2lvbiIsInZhbHVlIiwiU0NST0xMX0tFWSIsIklOREVYX0tFWSIsIlBSRUxPQURfUFJJT1JJVElFUyIsImdldF9iYXNlX3VyaSIsImRvYyIsImJhc2VVUkkiLCJiYXNlVGFncyIsInNjcm9sbF9zdGF0ZSIsImxpbmtfb3B0aW9uIiwiZWxlbWVudCIsIm5hbWUiLCJsZXZlbHMiLCJwYXJlbnRfZWxlbWVudCIsInBhcmVudCIsImZpbmRfYW5jaG9yIiwidGFyZ2V0IiwiZ2V0X2xpbmtfaW5mbyIsImEiLCJ1cmwiLCJleHRlcm5hbCIsImlzX2V4dGVybmFsX3VybCIsImdldF9yb3V0ZXJfb3B0aW9ucyIsIm5vc2Nyb2xsIiwicHJlbG9hZF9jb2RlIiwicHJlbG9hZF9kYXRhIiwicmVsb2FkIiwiZWwiLCJub3RpZmlhYmxlX3N0b3JlIiwic3RvcmUiLCJ3cml0YWJsZSIsInJlYWR5Iiwibm90aWZ5IiwidmFsIiwic2V0IiwibmV3X3ZhbHVlIiwic3Vic2NyaWJlIiwicnVuIiwib2xkX3ZhbHVlIiwiY3JlYXRlX3VwZGF0ZWRfc3RvcmUiLCJ0aW1lb3V0IiwiY2hlY2siLCJyZXMiLCJ1cGRhdGVkIiwiaW5pdCIsIm9wdHMiLCJzdG9yZXMiXSwibWFwcGluZ3MiOiJ3Q0FBTyxJQUFJQSxFQUFTLEdBQ1RDLEVBQU8sR0FFUEMsRUFBVSxHQVlkLFNBQVNDLEVBQVVDLEVBQU8sQ0FDaENILEVBQU9HLEVBQU0sS0FDYkosRUFBU0ksRUFBTSxRQUFVSCxDQUMxQixDQWtCTyxTQUFTSSxFQUFZQyxFQUFPLENBQ2xDSixFQUFVSSxDQUNYLENDdENZLE1BQUNDLEVBQWEsbUJBQ2JDLEVBQVksa0JBRVpDLEVBQTJDLENBQ3ZELElBQUssRUFDTCxNQUFPLEVBQ1AsU0FBVSxFQUNWLE1BQU8sRUFDUCxJQUFLLEVBQ04sRUNETyxTQUFTQyxFQUFhQyxFQUFLLENBQ2pDLElBQUlDLEVBQVVELEVBQUksUUFFbEIsR0FBSSxDQUFDQyxFQUFTLENBQ2IsTUFBTUMsRUFBV0YsRUFBSSxxQkFBcUIsTUFBTSxFQUNoREMsRUFBVUMsRUFBUyxPQUFTQSxFQUFTLENBQUMsRUFBRSxLQUFPRixFQUFJLEdBQ25ELENBRUQsT0FBT0MsQ0FDUixDQUVPLFNBQVNFLEdBQWUsQ0FDOUIsTUFBTyxDQUNOLEVBQUcsWUFDSCxFQUFHLFdBQ0wsQ0FDQSxDQXVCQSxTQUFTQyxFQUFZQyxFQUFTQyxFQUFNLENBU25DLE9BUENELEVBQVEsYUFBYSxrQkFBa0JDLEdBQU0sQ0FRL0MsQ0F5QkEsTUFBTUMsRUFBUyxDQUNkLEdBQUdULEVBQ0gsR0FBSUEsRUFBbUIsS0FDeEIsRUFNQSxTQUFTVSxFQUFlSCxFQUFTLENBQ2hD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
