import{w as s}from"./index-49b214d9.js";const o=s([]);function n(l){const t=new r(l);return o.update(e=>[t,...e]),t}class r{constructor(t){var e,i,c;this.text=s(t.text),this.type=s((e=t.type)!=null?e:"none"),this.closeable=s((i=t.showCloseButton)!=null?i:!0),this.closeOnClick=(c=t.closeOnClick)!=null?c:!1,this.onAlertClose=t.onClose,this.onAlertClick=t.onClick}click(){this.onAlertClick&&this.onAlertClick(this),this.closeOnClick&&this.close()}close(){console.log("close",this),o.update(t=>t.filter(e=>e!==this)),this.onAlertClose&&this.onAlertClose(this)}}export{o as a,n as s};