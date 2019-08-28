package com.basic;

import com.domain.Apple;
import com.util.IniProperties;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//基本类型
public class BasicType {
    private boolean boo;

    //boolean初始化false
    @Test
    void test1() {
        System.out.println("...");
        boo = true;
        System.out.println(boo);
    }

    @Test
    void test2() {
        int a = 10;
        int b;
        int c;
        if (a > 50) {
            b = 9;
        }
//        c = b + c;//使用局部变量时必须设置初始值
    }

    @Test
    void test3() {//== 优先级大于三目运算符
        int a = 1 > 2 ? 5 : 3 == 3 ? 0 : 1;
        System.out.println(a);
    }

    @Test
    void test4() {
        float f = 5.1f;
        int i = (byte) f;
        byte b = 2;
        i = b;
    }

    @Test
    void test5() {
//        List<Object> list1 = new ArrayList<String>();
        ArrayList<?> list2 = new ArrayList<Object>();
        ArrayList<? extends Number> list3 = new ArrayList<Integer>();
    }

    @Test
    void test6() {
        byte b1 = 1, b2 = 2, b3, b6;
        final byte b4 = 4, b5 = 6;
        b6 = b4 + b5;//final修饰的变量，编译器优化b6=10;
//        b3 = (b1 + b2);//byte,short,char计算时先转换为int，有更高的操作数存在则转换为高的一方
//        System.out.println(b6 + b3);
    }

    void test7() {
        Runnable runnable = () -> {
            System.out.println("s123");
        };

        Object o = new Object(){
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }
}
