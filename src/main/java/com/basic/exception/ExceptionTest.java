package com.basic.exception;

public class ExceptionTest {
    public static String outStr = "";

    public static int test1() {
        int i = 0;
        if (i == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
//                return 1;
            }
        }
        System.out.println("exception after");
        return 2;
    }

    public static String test2(int i) {
        try {
            if (i == 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            return outStr += "2";
        } finally {
            outStr += "3";
        }
        return outStr += "4";
    }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println("=======");
        System.out.println("test2(0)===" + test2(0));
        System.out.println("test2(1)===" + test2(1));
        System.out.println(outStr);
    }
}
