package com.basic.thread.deadlock;

/**
 * @author xsy
 * @create 2019-03-13 14:32
 * @desc
 **/
public class Client {
    public static void main(String[] args) {
        TheTest theTest1 = new TheTest();
        TheTest theTest2 = new TheTest();
        TestLock testLock1 = new TestLock(theTest1, theTest2);
        TestLock testLock2 = new TestLock(theTest2, theTest1);
        new Thread(testLock1).start();
        new Thread(testLock2).start();
    }

}
