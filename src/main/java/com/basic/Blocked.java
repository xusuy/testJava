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

    void testInner() {//局部内部类不能有访问控制修饰符和static
        int a = 1;
        final int b = 11;
        int inAge = new NeedsCleanup(1).age;
        abstract class D {
            void dTest() {
//                int aa = a;   //局部内部类不能使用外部非final
                int bb = b;
//                a++;
//                inAge++;      //局部内部类不能使用外部非final
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
}
