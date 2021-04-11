package com.madas;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SymmetricKey {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //keysize
        keyGenerator.init(192);
        Key key = keyGenerator.generateKey();
        System.out.print("KEY:");
        Hash.printByte(key.getEncoded());

        byte[] input = repeat("Madasx!!", 16).getBytes(StandardCharsets.UTF_8);
        Hash.printByte(input);

        //Encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(input);
        System.out.println("Encrypted:");
        Hash.printByte(encrypted);

        //Decryption
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted::");
        Hash.printByte(decrypted);
    }

    public static String repeat(String x, int i) {
        String w = new String(x);
        for(int q=0;q<i;q++){
            x += w;
        }
        return x;
    }
}
