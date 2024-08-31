package com.example.al_rewaq;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String CHARSET = "UTF-8";

    private SecretKey secretKey;

    // Constructor to generate a new secret key
    public EncryptionUtil() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256); // Use 256-bit AES encryption key
        secretKey = keyGenerator.generateKey();
    }

    // Constructor to use an existing secret key
    public EncryptionUtil(String key) throws Exception {
        byte[] decodedKey = Base64.decode(key, Base64.DEFAULT);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    // Method to encrypt data
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(CHARSET));
        return Base64.encodeToString(encryptedData, Base64.DEFAULT);
    }

    // Method to decrypt data
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, CHARSET);
    }

    // Method to get the secret key as a string (to store and reuse)
    public String getSecretKey() {
        return Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
    }
}

