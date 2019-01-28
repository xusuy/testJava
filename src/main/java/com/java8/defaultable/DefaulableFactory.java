package com.java8.defaultable;

import java.util.function.Supplier;

/**
 * @author xsy
 * @create 2018-11-20 10:09
 * @desc
 **/
public interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaultable create(Supplier<Defaultable> supplier) {
        return supplier.get();
    }
}
