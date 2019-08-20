package com.java8;

import java.util.function.Supplier;

/**
 * @author xsy
 * @create 2018-11-19 17:38
 * @desc
 **/
public class Car {
    public static Car create(final Supplier<Car> supplier) {
        System.out.println("supplier " + supplier.get());
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car);
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another);
    }

    public void repair() {
        System.out.println("Repaired " + this);
    }
}

