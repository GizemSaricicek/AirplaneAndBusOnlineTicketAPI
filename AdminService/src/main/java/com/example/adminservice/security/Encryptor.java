package com.example.adminservice.security;

import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class Encryptor {

    public String encryptGivenPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDiggest = md.digest(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDiggest);
            return bigInteger.toString(16);

        } catch (NoSuchAlgorithmException e) {
            return "Some error occured.";
        }
    }
}
