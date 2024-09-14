package com.auth.test.authtest.key;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GenerateKey {
    public static void main(String[] args) {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        String secretKey = new BigInteger(1, bytes).toString(16);
        System.out.println(secretKey);
    }
}
