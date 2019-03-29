package com.basic;

import org.junit.Test;

/**
 * @author xsy
 * @create 2019-03-13 10:35
 * @desc 一些代码技巧方法
 **/
public class SkillTest {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0)
                    System.out.print(j);
                else
                    break;
            }
        }
    }

    @Test
    public void test0uter() {
        outer:
        // outer：直接跳出outer指定的循环
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0)
                    System.out.print(j);
                else
                    break outer;
            }
        }
    }
    @Test
    public void test2() {
        String strArray[] = new String[6];
//        for (; ; ) {//死循环
//
//        }
        for (String newArray[] = strArray; ; ) {////死循环
            System.out.println("66");
        }
    }

}
