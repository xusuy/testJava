package com.basic.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author xsy
 * @create 2019-02-27 10:45
 * @desc 线程阻塞中断，当一个线程处于被阻塞状态或者试图执行一个阻塞操作时，
 * 使用Thread.interrupt()方式中断该线程，注意此时将会抛出一个InterruptedException的异常，
 * 同时中断状态将会被复位(由中断状态改为非中断状态)，如下代码将演示该过程
 *
 **/
public class InterruputSleepThread3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interruted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:" + interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }
}
