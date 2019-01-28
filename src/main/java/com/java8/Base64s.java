package com.java8;

/**
 * @author xsy
 * @create 2018-11-20 14:00
 * @desc Java 8 base64支持
 **/

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64s {
    public static void main(String[] args) {
        final String text = "Base64 finally in Java 8!";

        //加密
        final String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        //解密
        final String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}

