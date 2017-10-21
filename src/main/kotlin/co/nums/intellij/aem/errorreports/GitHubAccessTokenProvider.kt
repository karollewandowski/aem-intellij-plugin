package co.nums.intellij.aem.errorreports

import org.apache.commons.codec.binary.Base64
import java.io.*
import javax.crypto.Cipher
import javax.crypto.spec.*

object GitHubAccessTokenProvider {

    private const val ENCRYPTED_TOKEN_FILE_PATH = "errorReporting/GitHubToken"

    private const val KEY = "MustBe16BytesKey"
    private const val INIT_VECTOR = "RandomInitVector"

    val token: String by lazy {
        val encryptedToken = readEncryptedToken()
        decrypt(encryptedToken)
    }

    private fun readEncryptedToken(): String {
        val resource = GitHubAccessTokenProvider::class.java.classLoader.getResource(ENCRYPTED_TOKEN_FILE_PATH)
                ?: throw IOException("Could not get access token")
        val inputStream = ObjectInputStream(resource.openStream())
        return inputStream.readObject() as String
    }

    private fun decrypt(encryptedToken: String): String {
        val cipher = createCipher()
        return String(cipher.doFinal(Base64.decodeBase64(encryptedToken)))
    }

    private fun createCipher(): Cipher {
        val keySpec = SecretKeySpec(KEY.toByteArray(charset("UTF-8")), "AES")
        val ivParameter = IvParameterSpec(INIT_VECTOR.toByteArray(charset("UTF-8")))
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameter)
        return cipher
    }

}
