package com.basic.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

//原子类测试
public class AtomicTest {

    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    @Test
    public void test1(){
        //如果nextServerCyclicCounter的当前值等于期望的值则更新为指定的值，并返回true
        boolean boo1 = nextServerCyclicCounter.compareAndSet(0, 1);
        System.out.println(boo1);
//        System.out.println(incrementAndGetModulo(10));
    }
    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
        } while(!this.nextServerCyclicCounter.compareAndSet(current, next));

        return next;
    }
}
