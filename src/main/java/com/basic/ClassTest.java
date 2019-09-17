package com.basic;

import org.junit.Test;

/**
 * @author xsy
 * @create 2017-02-25 16:53
 * @desc class类加载
 **/
public class ClassTest {
    static int i;
    String str;
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
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
    }

    static void f1() {
    }

    void f2() {
    }

    class ClassTestSub {//成员内部类
        public void main(String[] args){//内部类 不能有静态的定义；可以直接访问外部类的成员和静态内容
            main(new String[]{"1"});
            f1();
            f2();
            System.out.println(i);
            System.out.println(str);
        }

    }

    static class ClassTestSubStatic {//静态内部类
        public static void main(String[] args){

        }
        void f3(){//静态内部类不能访问外部的成员
//            f2();
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
