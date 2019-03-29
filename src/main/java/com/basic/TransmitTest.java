package com.basic;

import com.domain.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xsy
 * @create 2017-02-21 9:44
 * @desc 值传递/引用传递
 **/
public class TransmitTest {
    public static void main(String[] args) {
        //值传递：基本数据类型传值，对形参的修改不会影响实参；
        int t1 = 1;
        change(t1);
        System.out.println(t1);
        //引用传递：引用类型传引用，形参和实参指向同一个内存地址（同一个对象），所以对参数的修改会影响到实际的对象
        User user = new User();
        user.setId(1);
        user.setEmail("123");
        user.setUsername("user1");
        change(user);
        System.out.println(user.getUsername());
        //String 等特殊引用类型：会创建新的对象，对原对象不会有影响
        String str1 = "s1";
        change(str1);
        System.out.println(str1);

        //TODO 为什么说java中只有值传递，而没有引用传递呢？
        //技术是java中的引用传递，也是将实参的对象引用地址复制了一份副本给了形参，而不是实参本身，对实参本身没有影响
    }

    private static void change(String str1) {
        str1 = "s2";
    }

    private static void change(int t1) {
        t1 = 2;
    }

    private static void change(User user) {
        user.setUsername("user2");
    }

    @Test
    public void test1() {
        Map map = new HashMap();
        map.put(1, 11);
        test2(map);
        System.out.println(map);
    }

    Map test2(Map map) {
        map.put(2, 22);
        return map;
    }
}
