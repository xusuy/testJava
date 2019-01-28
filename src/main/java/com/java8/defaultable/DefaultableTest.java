package com.java8.defaultable;

/**
 * @author xsy
 * @create 2018-11-20 10:10
 * @desc 测试类
 **/
public class DefaultableTest {
    public static void main(String[] args) {
        Defaultable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }

}
