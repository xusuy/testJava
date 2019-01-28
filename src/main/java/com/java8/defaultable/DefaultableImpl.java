package com.java8.defaultable;

/**
 * @author xsy
 * @create 2018-11-20 10:01
 * @desc 默认方法使得开发者可以在 不破坏二进制兼容性的前提下，往现存接口中添加新的方法，
 *      即不强制那些实现了该接口的类也同时实现这个新加的方法。
 **/
public class DefaultableImpl implements Defaultable {

    private void test(){
        new DefaultableImpl().notRequired();//接口默认的方法可以不去重写
    }

    @Override
    public void t1() {

    }
}
