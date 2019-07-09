package com.basic.thread.procuctConsume;

/**
 * @author xsy
 * @desc 测试生产者消费者
 **/
public class TestConsumerAndProducer {
    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产线程
        Producer producer = new Producer(resource);
        //消费线程
        Consumer consumer = new Consumer(resource);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
