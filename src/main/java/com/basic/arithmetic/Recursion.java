package com.basic.arithmetic;

import org.junit.Test;

/**
 * @author xsy
 * @create 2019-03-29 15:06
 * @desc 递归
 **/
public class Recursion {

    /**
     * 阶乘函数
     *
     * @param n
     * @return
     */
    int fact(int n) {
        if (n < 0)
            return 0;
        else if (n == 0 || n == 1)
            return 1;
        else
            return n * fact(n - 1);
    }

    @Test
    public void test1() {
        System.out.println(fact(5));
    }
}
