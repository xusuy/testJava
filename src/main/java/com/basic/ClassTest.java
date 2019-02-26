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
            Animal animal = (Animal) dog1;
            System.out.println("dog1 is animal");
        }
        if (dog2 instanceof Animal) {
            Animal animal = (Animal) dog2;
            System.out.println("dog2 is animal");
        }
    }

}

interface Animal {
}

class Dog implements Animal {
}
