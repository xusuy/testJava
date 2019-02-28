package com.basic.thread;

/**
 * @author xsy
 * @create 2017-02-27 11:20
 * @desc 多实例多线程：一个线程 A 需要访问实例对象 obj1 的 synchronized 方法 f1(当前对象锁是obj1)，
 * 另一个线程 B 需要访问实例对象 obj2 的 synchronized 方法 f2(当前对象锁是obj2)，这样是允许的
 **/
public class AccountingSyncBad implements Runnable {
    static int i = 0;

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
        //new新实例
        Thread t1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
