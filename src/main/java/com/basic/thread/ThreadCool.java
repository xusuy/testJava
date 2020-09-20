package com.basic.thread;

import java.util.concurrent.*;

/**
 * @Author： xusy
 * @Date 2020/7/13 11:02
 * @Version 1.0
 * @Describe
 */
public class ThreadCool {
    public void threadCoolConfig() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
    }
}
