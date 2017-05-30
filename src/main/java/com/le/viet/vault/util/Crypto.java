package com.le.viet.vault.util;

import com.le.viet.vault.exception.ValidationException;
import com.le.viet.vault.exception.VaultException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import static com.le.viet.vault.model.common.Common.VALIDATION_EXCEPTION;

/**
 * Created by onelazyguy on 5/21/17.
 * reference: http://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryption
 */
public class Crypto {
    public static void main(String[] args) {
        final String password = "072367482bc251d8a9b78bbaea88f10b9a64970556d85c6e09fe3600b2af9de4"; //"my_password";
        final String salt = "771b1bdc2d064f0e"; //KeyGenerators.string().generateKey();
        String unencrypted = "VIET";

        test();

        /*TextEncryptor encryptor = Encryptors.text(password, salt);
        System.out.println("Salt: \"" + salt + "\"");

        System.out.println("Original text: \"" + unencrypted + "\"");

        String encryptedText = encryptor.encrypt(unencrypted);
        System.out.println("Encrypted text: \"" + encryptedText + "\"");

        System.out.println("------------------------------------------------");

        TextEncryptor dencryptor = Encryptors.text(password, salt);
        String decryptedText = dencryptor.decrypt(encryptedText);
        System.out.println("Decrypted text: \"" + decryptedText + "\"");

        if(unencrypted.equals(decryptedText)) {
            System.out.println("Success: decrypted text matches");
        } else {
            System.out.println("Failed: decrypted text does not match");
        }*/
    }

    public static void test(){
        final String masterPassword = "072367482bc251d8a9b78bbaea88f10b9a64970556d85c6e09fe3600b2af9de4"; //"my_password";
        final String salt = "771b1bdc2d064f0e"; //KeyGenerators.string().generateKey();
        String unencrypted = "az";

        String encrypted = encrypt(salt, masterPassword, unencrypted);
        System.out.println("Original text: " + unencrypted);
        System.out.println("Encrypted text: " + encrypted);
        System.out.println("------------------------------------------------");
        String decryptedText = decrypt(salt, masterPassword, encrypted);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String encrypt(String salt, String masterPassword, String unEncryptedText) {
        TextEncryptor encryptor = Encryptors.text(masterPassword, salt);
        String encryptedText;
        encryptedText = encryptor.encrypt(unEncryptedText);
        return encryptedText;
    }

    public static String decrypt(String salt, String masterPassword, String encryptedText) {
        TextEncryptor dencryptor = Encryptors.text(masterPassword, salt);
        String deCryptedText;
        deCryptedText = dencryptor.decrypt(encryptedText);
        return deCryptedText;
    }
}
