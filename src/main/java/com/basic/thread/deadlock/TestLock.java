package com.basic.thread.deadlock;

/**
 * @author xsy
 * @create 2019-03-13 14:27
 * @desc
 **/
public class TestLock implements Runnable {
    private TheTest theTest1;
    private TheTest theTest2;

    public TestLock(TheTest theTest1, TheTest theTest2) {
        this.theTest1 = theTest1;
        this.theTest2 = theTest2;
    }

    @Override
    public void run() {
//        theTest1.method(theTest2);//发生死锁
        theTest1.method3(theTest2);
    }
}
