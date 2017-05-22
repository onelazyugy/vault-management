package com.le.viet.vault.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 * Created by onelazyguy on 5/21/17.
 * reference: http://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryption
 */
public class Crypto {
    public static void main(String[] args) {
        final String password = "my_password";
        final String salt = KeyGenerators.string().generateKey();

        TextEncryptor encryptor = Encryptors.text(password, salt);
        System.out.println("Salt: \"" + salt + "\"");

        String textToEncrypt = "my unencrypted secret";
        System.out.println("Original text: \"" + textToEncrypt + "\"");

        String encryptedText = encryptor.encrypt(textToEncrypt);
        System.out.println("Encrypted text: \"" + encryptedText + "\"");

        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("Decrypted text: \"" + decryptedText + "\"");

        if(textToEncrypt.equals(decryptedText)) {
            System.out.println("Success: decrypted text matches");
        } else {
            System.out.println("Failed: decrypted text does not match");
        }
    }
}
