package com.basic;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author xsy
 * @create 2017-02-25 16:53
 * @desc class类加载
 **/
public class ClassTest {
    static int i;
    String str;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        str=str+"";
        Dog dog1 = null;
        Dog dog2 = new Dog();
        if (dog1 instanceof Animal) {
            Animal animal = dog1;
            System.out.println("dog1 is animal");
        }
        if (dog2 instanceof Animal) {
            Animal animal = dog2;
            System.out.println("dog2 is animal");
        }
        Dog blackDog = new BlackDog();
        if (blackDog instanceof BlackDog) {
            System.out.println("blackDog is BlackDog");
        }
        Dog.t1();
        blackDog.t1();
        Dog d1 = null;
        System.out.println(d1.i);//没有空指针异常
//        d1.t2();//NullPointerException
        Dog dog = Dog.class.newInstance();
        try {
            Method method_t2 = Dog.class.getMethod("t2", new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static void f1() {
        //'com.basic.ClassTest.this' cannot be referenced from a static context
//        ClassTestSub classTestSub = new ClassTestSub();
//        classTestSub.f3();
    }

    void f2() {
        ClassTestSub classTestSub = new ClassTestSub();
        classTestSub.f3();
    }

    class ClassTestSub {//成员内部类

//        public static void staticMethod() {//Inner classes cannot have static declarations
//
//        }

        private void f3() {
            //在同一个public类下 只要不是在静态上下文里都可以直接创建成员内部类，不需要显示创建外部类对象
            ClassTestSub classTestSub = new ClassTestSub();
            classTestSub.f3();
        }

        public void test() {//内部类 不能有静态的定义；可以直接访问外部类的成员和静态内容
            f3();
            f1();
            f2();
            System.out.println(i);
            System.out.println(str);
            new ClassTestSubStatic().f4();//可以直接创建静态内部类对象，不需要外部类对象
        }

    }

    static class ClassTestSubStatic {//静态内部类

        public static void main(String[] args) {
            //访问成员内部类需要使用外部类对象(在静态上下文里)
            ClassTest classTest = new ClassTest();
            ClassTestSub classTestSub = classTest.new ClassTestSub();
            //同一个public类下可以访问私有方法
            classTestSub.f3();

            //'com.basic.ClassTest.this' cannot be referenced from a static context
//            ClassTestSub classTestSub1 = new ClassTestSub();
//            classTestSub1.f3();
        }

        void f4() {
//            f2();//静态内部类不能直接访问外部的成员
            f1();
        }
    }

}

interface Animal {
}

class Dog implements Animal {
    static int i;

    static void t1() {//静态方法中可以通过new 访问非静态内容
        Dog dog = new Dog();
        System.out.println("dog static t1");
        dog.t2();
    }

    void t2() {
        System.out.println("dog Non-static t2");
        try {
            Animal animal = Animal.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class BlackDog extends Dog {

}
