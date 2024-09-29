package com.tcna.primeraweb;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //Creamos una variable de tipo BCryptPasswordEncoder
        String rawPassword = "password";
        String encodePassword = encoder.encode(rawPassword);

        System.out.println(encodePassword);
    }
}
