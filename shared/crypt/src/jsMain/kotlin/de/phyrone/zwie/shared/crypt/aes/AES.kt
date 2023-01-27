package de.phyrone.zwie.shared.crypt.aes

import CipherOption
import CipherParams
import de.phyrone.zwie.shared.crypt.utils.toByteArray
import de.phyrone.zwie.shared.crypt.utils.toWordArray
import global.CryptoJS.lib.CipherParamsPartial
import global.CryptoJS.lib.WordArray

actual object AES {
    actual fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {
        return global.CryptoJS.AES.encrypt(data.toWordArray(), key.toWordArray(), object : CipherOption {
            override var iv: WordArray? = iv.toWordArray()
        }).ciphertext.toByteArray()
    }

    actual fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {
        val params = CipherParams.create(object : CipherParamsPartial {
            override var ciphertext: WordArray? = data.toWordArray()
            override var key: WordArray? = key.toWordArray()
            override var iv: WordArray? = iv.toWordArray()
        })
        return global.CryptoJS.AES.decrypt(params, key.toWordArray()).toByteArray()
    }

}