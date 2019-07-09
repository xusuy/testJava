package com.basic.thread.procuctConsume;

/**
 * @author xsy
 * @desc 生产的资源或者消费的资源
 **/
public class Resource {
    /**
     * 当前数量
     */
    private int num = 0;

    /**
     * 资源上线数量
     */
    private int size = 10;

    //生产资源
    public synchronized void put() {
        while (num == size) {
            try {
                System.out.println("生产者进入等待");
                this.wait();
            } catch (InterruptedException intException) {
                intException.printStackTrace();
            }
        }
        num++;
        System.out.println("生产者线程为：" + Thread.currentThread().getName() + "；资源数量：" + num);
        //唤醒其他正在等待的线程（唤醒消费者线程）
        this.notify();
    }

    //消费资源
    public synchronized void remove() {
        while (num == 0) {
            try {
                System.out.println("消费者进入等待");
                this.wait();
            } catch (InterruptedException intException) {
                intException.printStackTrace();
            }
        }
        num--;
        System.out.println("消费者线程为：" + Thread.currentThread().getName() + "；资源数量：" + num);
        //唤醒其他正在等待的线程（唤醒生产者线程）
        this.notify();
    }
}
