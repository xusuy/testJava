package com.basic.thread.procuctConsume;

/**
 * @author xsy
 * @desc 消费者
 **/
public class Consumer implements Runnable {
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            resource.remove();
            try {
                Thread.sleep(100);
            } catch (InterruptedException inException) {
                inException.printStackTrace();
            }
        }
    }
}
