import de.phyrone.zwie.shared.crypt.MultiplatformSecureRandom
import de.phyrone.zwie.shared.crypt.aes.AES
import de.phyrone.zwie.shared.crypt.gpg.GPG
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TestCommon {


    @Test
    fun testHello() {
        println("hello world")
    }

    @Test
    fun testRandomBytes() {
        println(MultiplatformSecureRandom.nextByteArray(16))
    }

    @Test
    fun testAes() {
        val key = MultiplatformSecureRandom.nextByteArray(16)
        val iv = MultiplatformSecureRandom.nextByteArray(16)
        val firstMessage = "Hello World".encodeToByteArray()
        val encrypted = AES.encrypt(firstMessage, key, iv)

        val decrypted = AES.decrypt(encrypted, key, iv)
        assertContentEquals(firstMessage, decrypted)
    }


}