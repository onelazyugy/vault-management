package com.le.viet.vault.util;

import com.le.viet.vault.exception.VaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.security.MessageDigest;

/**
 * Created by onelazyguy on 1/6/17.
 */
public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static String hash(String password) throws VaultException {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            LOG.info("Hex format : " + sb.toString());
            return sb.toString();
        } catch (Exception e){
            throw new VaultException(e.getMessage(), 0);
        }
    }

    public static String generateSalt(){
        return KeyGenerators.string().generateKey();
    }

    public static String encrypt(String salt, String masterPassword, String unEncryptedText){
        TextEncryptor encryptor = Encryptors.text(masterPassword, salt);
        String encryptedText = encryptor.encrypt(unEncryptedText);
        return encryptedText;
    }

    public static String decrypt(String salt, String masterPassword, String encryptedText){
        TextEncryptor encryptor = Encryptors.text(masterPassword, salt);
        String deCryptedText = encryptor.encrypt(encryptedText);
        return deCryptedText;
    }
}
