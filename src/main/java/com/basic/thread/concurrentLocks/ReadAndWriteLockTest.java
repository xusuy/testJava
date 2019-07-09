package com.basic.thread.concurrentLocks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xsy
 * @create 2019-06-10 10:45
 * @desc 可重入读写锁（ReentrantReadWriteLock）：
 *      1.Java并发库中ReetrantReadWriteLock实现了ReadWriteLock接口并添加了可重入的特性
 *      2.ReetrantReadWriteLock读写锁的效率明显高于synchronized关键字
 *      3.ReetrantReadWriteLock读写锁的实现中，读锁使用共享模式；写锁使用独占模式，换句话说，读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的
 *      4.ReetrantReadWriteLock读写锁的实现中，需要注意的，当有读锁时，写锁就不能获得；而当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁
 *      5.一个线程获取多少次锁，就必须释放多少次锁。这对于内置锁也是适用的，每一次进入和离开synchornized方法(代码块)，就是一次完整的锁获取和释放。
 **/
public class ReadAndWriteLockTest {

    /**
     * 普通synchronized
     *
     * @param thread
     */
    public static synchronized void synGet(Thread thread) {
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
    }

    /**
     * ReentrantReadWriteLock：ReetrantReadWriteLock的效率明显高于Synchronized关键字；
     * ReetrantReadWriteLock读锁使用共享模式，即：同时可以有多个线程并发地读数据。
     *
     * @param thread
     */
    public static void reenGet(Thread thread) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.readLock().unlock();
    }

//    public static void main(String[] args) {
//        new Thread(() -> synGet(Thread.currentThread())).start();
//        new Thread(() -> synGet(Thread.currentThread())).start();
//    }

     public static void main(String[] args) {
        new Thread(() -> reenGet(Thread.currentThread())).start();
        new Thread(() -> reenGet(Thread.currentThread())).start();
    }

}
