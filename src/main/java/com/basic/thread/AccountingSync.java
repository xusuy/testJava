package com.basic.thread;

/**
 * @author xsy
 * @create 2017-02-27 11:16
 * @desc synchronized作用于实例方法
 * synchronized的三种应用方式：
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 * 原文：https://blog.csdn.net/javazejian/article/details/72828483
 **/
public class AccountingSync implements Runnable {
    //共享资源(临界资源)
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance = new AccountingSync();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
