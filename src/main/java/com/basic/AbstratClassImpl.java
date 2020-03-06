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

    public void testInnerClassMethod(){
        //外部 引用ClassTest的成员内部类需要ClassTest对象
//        ClassTest.ClassTestSub classTestSub1 = new ClassTest.ClassTestSub();
        ClassTest classTest = new ClassTest();
        ClassTest.ClassTestSub classTestSub2 = classTest.new ClassTestSub();
        //不能访问私有方法
//        classTestSub.f3();
    }
}
