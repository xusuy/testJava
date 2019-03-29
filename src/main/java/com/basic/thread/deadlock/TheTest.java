package com.basic.thread.deadlock;

/**
 * @author xsy
 * @create 2019-03-13 14:30
 * @desc Java发生死锁的根本原因是：在申请锁时发生了交叉闭环申请。即线程在获得了锁A并且没有释放的情况下去申请锁B，这时，
 * 另一个线程已经获得了锁B，在释放锁B之前又要先获得锁A，因此闭环发生，陷入死锁循环。
 **/
public class TheTest {
    public synchronized void method(TheTest theTest) {
        System.out.println("TestClass method in");
        theTest.method2();
        System.out.println("TestClass method out");
    }

    public synchronized void method2() {
        System.out.println("TestClass method2");
    }

    //调整锁的范围，避免死锁
    public void method3(TheTest theTest) {
        System.out.println("TestClass method in");
        synchronized (this) {
            //do something
        }
        theTest.method2();
        System.out.println("TestClass method out");
    }
}
