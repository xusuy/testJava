package com.basic.thread.concurrentLocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xsy
 * @desc 可重入锁：一个线程在持有一个锁的时候，它内部能否再次（多次）申请该锁。
 * 如果一个线程已经获得了锁，其内部还可以多次申请该锁成功。那么我们就称该锁为可重入锁。
 **/
public class ReentrantLockDemo01 implements Runnable {
    private Lock lock = new ReentrantLock();
    private int tickets = 200;

    @Override
    public void run() {
        while (true) {
            //获取锁
            lock.lock();
            try {
                if (tickets > 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "：" + tickets--);
                } else {
                    break;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo01 reentrantLockDemo01 = new ReentrantLockDemo01();
        for (int i = 0; i < 10; i++) {
            new Thread(reentrantLockDemo01, "thread" + (i + 1)).start();
        }
    }

}
