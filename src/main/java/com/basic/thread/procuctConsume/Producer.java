package com.basic.thread.procuctConsume;

/**
 * @author xsy
 * @desc 生产者
 **/
public class Producer implements Runnable {
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            resource.put();
            try {
                Thread.sleep(10);
            } catch (InterruptedException inException) {
                inException.printStackTrace();
            }
        }
    }
}
