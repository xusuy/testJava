package com.basic;

/**
 * @author xsy
 * @create 2017-02-25 20:49
 * @desc 一个抽象类
 **/
public abstract class AbstratClass {

    AbstratClass() {

    }

    public abstract void t1();

    public void t2() {
        System.out.println("AbstratClass====t2");
    }

    public AbstratClass t3() {
        return this;
    }

    ;
}
