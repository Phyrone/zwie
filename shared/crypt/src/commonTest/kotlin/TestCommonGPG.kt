import de.phyrone.zwie.shared.crypt.MultiplatformSecureRandom
import de.phyrone.zwie.shared.crypt.aes.AES
import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TestCommonGPG {


    @Test
    fun testEncryption() {
        runSuspend {
            val key = GPG.generateKey()
            GPG.encrypt(byteArrayOf(), key.getPublicKey())
        }
    }


}

expect fun <T> runSuspend(block: suspend () -> T): T