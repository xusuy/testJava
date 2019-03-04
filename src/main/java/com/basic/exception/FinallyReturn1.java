package com.basic.exception;

/**
 * @author xsy
 * @create 2019-03-01 11:05
 * @desc return与finally执行顺序测试
 **/
public class FinallyReturn1 {

    private static int i = 0;

    public static int finallyTest() {
        try {
            System.out.println("enter try block");
            i += 1;
            return i;
        } finally {
            System.out.println("enter finally block");
            i += 1;
            //return i;
        }
    }

    public static void main(String[] args) {
        System.out.println(finallyTest());
        System.out.println(i);
    }

}
