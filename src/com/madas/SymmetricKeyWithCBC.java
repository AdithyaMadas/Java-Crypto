package com.madas;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricKeyWithCBC {
    //CBC stands for Cipher block chaining
    public static void main(String[] args) throws Exception {

        //Generating AES KEY
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(192);
        Key key = keyGenerator.generateKey();
        Hash.printByte(key.getEncoded());

        //Input
        byte[] input = SymmetricKey.repeat("Madasx!!", 16).getBytes(StandardCharsets.UTF_8);
        Hash.printByte(input);

        //Make IV
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] random = new byte[16];
        secureRandom.nextBytes(random); // generates random byte array
        IvParameterSpec ivParameterSpec = new IvParameterSpec(random);
        System.out.println("\nIV::");
        Hash.printByte(ivParameterSpec.getIV());

        //encryption
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(input);
        System.out.println("\nEncrypted::");
        Hash.printByte(encrypted);

        //decryption
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("\ndecrypted::");
        Hash.printByte(decrypted);

    }
}
