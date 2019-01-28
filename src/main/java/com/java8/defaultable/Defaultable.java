package com.java8.defaultable;

/**
 * @author xsy
 * @create 2018-11-20 9:58
 * @desc 默认方法
 **/
public interface Defaultable {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default implementation";
    }

    void t1();
}


