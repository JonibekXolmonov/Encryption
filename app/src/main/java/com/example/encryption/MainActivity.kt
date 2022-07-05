package com.example.encryption

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.KeyStoreTask
import com.example.keystore.KeyStore
import com.example.unituitesting.symmetric.Asymmetric
import com.example.unituitesting.symmetric.Asymmetric.Companion.decryptMessage
import com.example.unituitesting.symmetric.Asymmetric.Companion.encryptMessage
import java.util.*

class MainActivity : AppCompatActivity() {

    val privateMobileKey =
        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL6s5kVsU2R/Vq6GPG/yohPFOBCUAYNbxJmr+0otm0RmFyoPhseByPLN5SXygkJSaxOWBTTQqrsls+9W5lmfHDALBZiD9nt/r0IgxAEKxDyQEJ7zNHfgLEnJ1AKdtv5eu494GSu8MFmqK8pXihyAwNG8dRj1H76t3PoCb1l8k+orAgMBAAECgYA3Rrpuz8uRK9U+PQ4SSh2wa5EoRS/3G8hv4Eq2I2iKuKnoeJ3TAo31zbirepVGkswV3nzd5cxI+tIiBWg7/QNXbg1NQtNOUTVoaGJN3OKyJyvudoKvgLYPenuLe0g3syBTh/CVIdTIRc01XoagriGK88bjx5bU+44SazVnAiU/8QJBAOBLVbjiH7eNQ2YFcF0wsgZoJl4VYWdH5zW6zDgkCZxpWSDf/Un/jwMia1TnAqb76rXkW2OpfKH6uiYvU9qVVxMCQQDZoPqOKumpArnkP9k/oZgs8//7ERMBBC6dKpvA5OFyFK3orYuaOWMKPfkNmvWs169DqRFYdP9GjPGENfeZO4uJAkAgObKvRY3ZCWrNlap3OS/ay8v3aIr+E86cYaJFzqey2U/88TGrGBSsFp3lLOusojUuvQiEYS2O+D8WjWY22Y4RAkBsgdrrL3I5Fmbg6wTCuStKno2u/KBhDVjAfBIUZwoQ+0aKkJzeVGaRIL0gj3aWqyNztMQ1OoBdCMESjmXZPNshAkB4spUCNvNkMg0/26YA6jqdUs2QxI5S85iklXJjnXDVkGVRlW+1GzISu3vczU8nif3kNFEb29wgM273W7CEoJR2"
    val publicMobileKey =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+rOZFbFNkf1auhjxv8qITxTgQlAGDW8SZq/tKLZtEZhcqD4bHgcjyzeUl8oJCUmsTlgU00Kq7JbPvVuZZnxwwCwWYg/Z7f69CIMQBCsQ8kBCe8zR34CxJydQCnbb+XruPeBkrvDBZqivKV4ocgMDRvHUY9R++rdz6Am9ZfJPqKwIDAQAB"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testAsymmetric()
        //justTest()

        justTest2()

    }

    private fun justTest2() {
        val pairKey = KeyStore(this).createNewKeys("FootZoneApplication")
        Log.d("TAG", "justTest2: ${pairKey?.public}")
        Log.d("TAG", "justTest2: ${pairKey?.private}")
    }

    private fun justTest() {
        val keyStoreTask = KeyStoreTask(this).instance()
        val generatedKey = keyStoreTask.generateSecretKey()
        Log.d("TAG", "onCreate: $generatedKey")

        val secretKey = keyStoreTask.getSecretKey()
        Log.d("TAG", "onCreate: $secretKey")

        val encryptedText = keyStoreTask.encrypt("Saidahmad")
        Log.d("TAG", "justTest: $encryptedText")

        val decryptedText = keyStoreTask.decrypt(encryptedText!!)
        Log.d("TAG", "justTest: $decryptedText")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun testAsymmetric() {
        val secretText = " Good luck to the brave!!! "
        val keyPairGenerator = Asymmetric()
        // Generate private and public key
        val privateKey: String =
            Base64.getEncoder().encodeToString(keyPairGenerator.privateKey.encoded)
        val publicKey: String =
            Base64.getEncoder().encodeToString(keyPairGenerator.publicKey.encoded)
        Log.d("@@@", "Private Key: $privateKey")
        Log.d("@@@", "Public Key: $publicKey")
        // Encrypt secret text using public key
        val encryptedValue = encryptMessage(secretText, publicKey)
        Log.d("@@@", "Encrypted Value: $encryptedValue")
        // Decrypt
        val decryptedText = decryptMessage(encryptedValue, privateKey)
        Log.d("@@@", "Decrypted output: $decryptedText")
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun testSymmetric() {
//        //secret text
//        val originalString = "Pdp academy"
//        // Encryption
//        val encryptedString = encrypt(originalString)
//        // Decryption
//        val decryptedString = decrypt(encryptedString)
//
//        Log.d("TAG", "Original String: $originalString")
//        Log.d("TAG", "Encrypted String: $encryptedString")
//        Log.d("TAG", "Decrypted String: $decryptedString")
//    }
}