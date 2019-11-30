package com.basic;

import org.junit.jupiter.api.Test;

import java.util.List;


class NeedsCleanup {
    public final int id;
    public int age;

    public NeedsCleanup(int ident) {
        id = ident;
    }

    void test() {
//        id =1;
    }
}

interface Car{
    void testCar(NeedsCleanup needsCleanup,String carName);
}

public class Blocked {

    static final class A {

    }

    public final class B {

    }

    private final class C {
        void v1() {
            NeedsCleanup needsCleanup = new NeedsCleanup(1);
            int age = needsCleanup.age;
            age++;
        }
    }

    @Test
    public void test() {
        NeedsCleanup n1 = new NeedsCleanup(1);
        NeedsCleanup n2 = new NeedsCleanup(2);
        System.out.println(n1.id);
        System.out.println(n2.id);
    }

    void testInner() {
        int a = 1;
        final int b = 11;
        int inAge = new NeedsCleanup(1).age;
        abstract class D {//局部内部类不能有访问控制修饰符和static
            void dTest() {
                int aa = a;
                int bb = b;
//                a++;
//                inAge++;      //局部内部类使用外部参数时会备份一份到内部来，并且把参数设置成final,不能改变其值
            }
        }
        final class E {

        }
//        public class G{
//
//        }
//        private class H{
//
//        }
//        static class I{
//
//        }
    }

    static void testPartParam(Car car){

    }

    public static void main(String[] args) {
        NeedsCleanup needsCleanup = new NeedsCleanup(1);
        testPartParam(new Car() {
            @Override
            public void testCar(NeedsCleanup needsCleanup, String carName) {
                System.out.println("======");
            }
        });
    }
}
