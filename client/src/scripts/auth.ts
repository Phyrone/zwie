import {writable} from "svelte/store";

export type AuthContext = "anonymous" | LoginAuthContext;

export const authContext = writable<AuthContext>("anonymous");

export class LoginAuthContext {
    constructor(public username: string) {
    }
}