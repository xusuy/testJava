package com.basic;

import org.junit.jupiter.api.Test;

/**
 * @author xsy
 * @create 2017-02-25 20:51
 * @desc
 **/
public class AbstratClassImpl extends AbstratClass {
    @Override
    public void t1() {

    }


//    @Override
//    public void t2(){
//        System.out.println("AbstratClassImpl====t2");
//    }

    @Override
    public AbstratClass t3() {
//        return new Object();
        return new AbstratClassImpl();//重写方法的返回类型只能小于或等于父类
    }
    @Test
    public void test(){
        t2();
    }
}
