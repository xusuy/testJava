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

    /**
     * 阶乘——尾递归
     *
     * @param n
     * @param res=1：维护递归层次的深度
     * @return
     */
    int facttail(int n, int res) {
        if (n < 0)
            return 0;
        else if (n == 0)
            return 1;
        else if (n == 1)
            return res;
        else
            return facttail(n - 1, n * res);
    }

    @Test
    public void test2() {
        System.out.println(facttail(5, 1));
    }
}
