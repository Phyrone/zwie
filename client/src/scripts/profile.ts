import * as constants from "constants";
import * as localforage from "localforage";
import {writable} from "svelte/store";
import {generateKey, createMessage, sign, readPrivateKey, createCleartextMessage} from 'openpgp/lightweight';





export const activeProfile = writable<ProfileData | null>(null);

const profile_store_key: string = "zwie::pofile";

(async () => {
    await localforage.ready()
    let settings = await localforage.getItem<ProfileSettingsData>(profile_store_key)
    if (settings) {
        console.log(settings)
    } else {
        await localforage.setItem<ProfileSettingsData>(profile_store_key, {
            currentProfile: undefined,
            availableProfiles: [],
        })
    }
})();

export async function createProfileData(name: string): Promise<ProfileData> {
    let {publicKey, privateKey} = await generateKey({
        curve: "curve25519",
        type: "ecc",
        format: "armored",
        userIDs: {name}
    })

    return {
        name,
        privateKey,
        publicKey,
    }
}

export type ProfileSettingsData = {
    readonly currentProfile?: ProfileData,
    readonly  availableProfiles: ProfileData[]
}

export type ProfileData = {
    readonly name: string,
    readonly  publicKey: string,
    readonly   privateKey: string,
}