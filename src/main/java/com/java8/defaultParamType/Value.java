package com.java8.defaultParamType;

/**
 * @author xsy
 * @create 2018-11-20 10:29
 * @desc
 **/
public class Value<T> {
    public static <T> T defaultValue() {
        return null;
    }

    public T getOrDefault(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }
}
