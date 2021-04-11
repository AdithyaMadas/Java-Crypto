package com.madas;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class SignatureTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair keyPair = kpGen.generateKeyPair();

        System.out.println("PUBLIC KEY:");
        Hash.printByte(keyPair.getPublic().getEncoded());
        System.out.println("PRIVATE KEY:");
        Hash.printByte(keyPair.getPrivate().getEncoded());

        String data = "Devoxx is the best!!!";

        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes());
        byte[] sign = signature.sign();
        Hash.printByte(sign);

//        sign[5] = 20;

        Signature verify = Signature.getInstance("SHA256WithRSA");
        verify.initVerify(keyPair.getPublic());
        verify.update(data.getBytes());
        System.out.println();
        System.out.println(verify.verify(sign));
    }
}
