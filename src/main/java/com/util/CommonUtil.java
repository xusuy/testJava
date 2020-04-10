package com.util;

import org.junit.Test;

import java.util.Random;

/**
 * @author xsy
 * @create 2020-04-09 11:32
 * @desc 公共工具类
 **/
public class CommonUtil {

    /**
     * 生成字母+数字的随机码
     *
     * @param digit 需要生成的位数
     * @return
     */
    public static String generateRandomCode(int digit) {
        String randomcode = "";
        String codes = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] chars = codes.toCharArray();
        int codesLength = codes.length();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            randomcode += chars[random.nextInt(codesLength)];
        }
        return randomcode;
    }

    @Test
    public void testRandomCode() {
        System.out.println(generateRandomCode(6));
    }
}
