import { error } from '@sveltejs/kit';
import LayoutData from './$types'


export async function load({params}: any) {
    return {
        server: params.server
    }
}
