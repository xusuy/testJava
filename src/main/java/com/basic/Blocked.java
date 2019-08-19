package com.basic;

import org.junit.jupiter.api.Test;

import java.util.List;


class NeedsCleanup {
    public final int id;

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
        abstract class D {
            void dTest(){
//                int aa = a;
                int bb = b;
//                a++;
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
