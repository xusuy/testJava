package com.basic;

/**
 * @author xsy
 * @create 2017-02-25 16:53
 * @desc class类加载
 **/
public class ClassTest {
    public static void main(String[] args) {
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
    }

}

interface Animal {
}

class Dog implements Animal {
    static void t1() {
        Dog dog = new Dog();
        System.out.println("dog static t1");
        dog.t2();
    }

    void t2() {
        System.out.println("dog static t2");
    }
}

class BlackDog extends Dog {

}
