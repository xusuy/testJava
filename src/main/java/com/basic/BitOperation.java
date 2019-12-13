package com.basic;

import org.junit.jupiter.api.Test;

/**
 * @author xsy
 * @create 2019-03-04 11:36
 * @desc 位运算
 **/
public class BitOperation {
    public static void main(String[] args) {
        int a = 4;
        int b = 3;
        int bit_with = a & b;//按位与操作：两个都为1才为1
        System.out.println(bit_with);
        int bit_or = a | b;
        System.out.println(bit_or);//按位或操作：只要有1就是1
        int bit_non = ~a;//按位非操作：取反
        //11111111 11111111 11111111 11111011 =  1011 = -1*(2)的三次幂+0*2的二次幂+1*2的一次幂+1*2的零次幂
        System.out.println(bit_non);
        int bit_other = a ^ bit_non;//按位异或操作：^ 异或操作符，相同位值为0 否则为1：如果两个数字的
        System.out.println(bit_other);
    }

    int add(int a, int b) //递归形式
    {
        if (b == 0) //递归结束条件：如果右加数为0，即不再有进位了，则结束。
            return a;
        int s = a ^ b;
        int c = (a & b) << 1; //进位左移1位，达到进位的目的。
        return add(s, c); //再把'和'和'进位'相加。递归实现。
    }

    @Test
    void test1() {
        int int1 = 132;
        System.out.println(Integer.toOctalString(int1));//十进制转八进制
        System.out.println(Integer.parseInt("13", 8));//八进制转十进制
        int i = 013 * 014;
        System.out.println(i);
    }
}
