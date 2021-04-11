package com.madas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] x = messageDigest.digest("Madas".getBytes(StandardCharsets.UTF_8));
        printByte(x);
        byte[] b = messageDigest.digest("Madas".getBytes(StandardCharsets.UTF_8));
        printByte(b);
        byte[] w = messageDigest.digest("Madaa".getBytes(StandardCharsets.UTF_8));
        printByte(w);
    }

    public static void printByte(byte[] x) {
        for (byte q : x) {
            char c = (char) q;
            System.out.print(c);
        }
        System.out.println(x.length);
    }
}
