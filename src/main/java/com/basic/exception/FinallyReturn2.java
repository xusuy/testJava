package com.basic.exception;

import java.lang.reflect.Field;

/**
 * @author xsy
 * @create 2019-03-01 11:07
 * @desc return与finally执行顺序测试
 **/
public class FinallyReturn2 {
    private static Integer i = new Integer(0);

    public static Integer finallyTest() throws IllegalArgumentException, IllegalAccessException {
        Integer j = new Integer(8);
        try {
            System.out.println("enter try block");
            i += 1;
            return i;
        } finally {
            System.out.println("enter finally block");
//          不影响return返回值
//            i = new Integer(66);
//            利用放射可以影响return的返回值
            Class<Integer> clazz = (Class<Integer>) i.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Field f = null;
            for (int i = 0; i < fields.length; i++) {
                if ("value".equals(fields[i].getName()))
                    f = fields[i];
            }
            f.setAccessible(true);
            f.setInt(i, 88);
        }
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        System.out.println(finallyTest());
//        System.out.println(i);
    }
}
