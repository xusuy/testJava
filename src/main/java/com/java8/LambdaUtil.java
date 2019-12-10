package com.java8;

import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author xsy
 * @create 2018-11-19 17:09
 * @desc Lambda测试
 **/
public class LambdaUtil {

    @Test
    public void testLambda1() {
        Arrays.asList("a", "b", "c").forEach((e) -> System.out.println(e));
    }

    //Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的），例如下列两个代码块的效果完全相同：
    @Test
    public void testLambda2() {
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.print(e + separator));
    }

    @Test
    public void testLambda3() {
        final String separator = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.print(e + separator));
    }

    @Test
    public void testLambda4() {
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));
    }


    public void testLambda5() {
        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

        // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();
    }

    /**
     * 比较器
     */
    public static void testJava7Compare() {
        // JDK7 匿名内部类写法
        List<String> list = Arrays.asList("I", "love", "you", "too", null, null);
        Collections.sort(list, new Comparator<String>() {// 接口名
            @Override
            public int compare(String s1, String s2) {// 方法名
                if (s1 == null)
                    return -1;
                if (s2 == null)
                    return -1;
                return s1.length() - s2.length();
            }
        });
        System.out.println(list);
    }

    public static void testLambdaCompare() {
        List<String> list = Arrays.asList("I", "love", "you", "too");
        Collections.sort(list, (s1, s2) -> {// 省略参数表的类型
            if (s1 == null)
                return -1;
            if (s2 == null)
                return -1;
            return s1.length() - s2.length();
        });
        System.out.println(list);
    }

    public static void main(String[] args) throws UnknownHostException {
        testJava7Compare();
        testLambdaCompare();
    }

    public void testLambda() {
        Runnable runnable1 = () -> System.out.println("123");
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        };
        Thread thread1 = new Thread(() -> System.out.println("456"));
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("456");
            }
        });
    }

    //测试lambda中的变量
    @Test
    public void lambdaVariable() {
        //引用局部变量，不能改变局部变量
        String s1 = "s1";
        int i1 = 1;
        new Thread(() -> {
            //Variable 's1' is accessed from within inner class, needs to be final or effectively final
            //s1 = "s11";
            String s2 = s1 + "s2";
            //i1 = 2;
            int i2 = i1;
            i2 = i2 + 1;
        });
        //在参数中使用的变量i，使用的是变量的副本
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        list1.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                i++;
                System.out.println(i);
            }
        });
    }

}
