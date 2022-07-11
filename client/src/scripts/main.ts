import App from '../components/App.svelte'



document.body.innerHTML = ''
window['app'] = new App({
    target: document.body
})


export default {}
