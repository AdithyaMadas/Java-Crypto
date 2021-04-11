package com.madas;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class AssymmetricKey {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair keyPair = kpGen.generateKeyPair();
        System.out.println("PUBLIC KEY:");
        Hash.printByte(keyPair.getPublic().getEncoded());
        System.out.println("PRIVATE KEY:");
        Hash.printByte(keyPair.getPrivate().getEncoded());


        byte[] text = "The Lord of the Rings has been read by many people".getBytes();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] encrypted = cipher.doFinal(text);
        System.out.println("Encrypted:");
        Hash.printByte(encrypted);

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        byte[] decrypted = cipher.doFinal(text);
        System.out.println("Decrypted:");
        Hash.printByte(decrypted);

    }
}
