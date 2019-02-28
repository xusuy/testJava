package com.basic.generic;

/**
 * @author xsy
 * @create 2018-02-28 17:09
 * @desc 泛形：参数化类型
 **/
public class Test<T> {

    public <E> void method1(E e) {

    }

    public <T> T method2(T t) {
        return t;
    }
}
