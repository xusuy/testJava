package com.frame.util;

import java.util.function.Function;

/**
 * @author xsy
 * @create 2019-08-21 15:21
 * @desc 类型工具
 **/
public class TypeUtil {
    public static <T, R> R convertType(T t, Function<T, R> function) {
        return function.apply(t);
    }

    public static void main(String[] args) {
        String str = "1";
        Integer strInt = convertType(str, s -> Integer.valueOf(s));
        System.out.println(strInt);
    }
}
