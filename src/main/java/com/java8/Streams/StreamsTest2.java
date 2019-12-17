package com.java8.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author xsy
 * @create 2019-05-06 9:47
 * @desc
 **/
public class StreamsTest2 {

    private static List<User> list = new ArrayList<>();

    public static void main(String[] args) {
        list = Arrays.asList(
                new User(1, "a", 10),
                new User(4, "d", 19),
                new User(5, "e", 13),
                new User(2, "b", 14),
                new User(3, "a", 10),
                new User(6, "f", 16)
        );

        long start = System.currentTimeMillis();

        multiConditionDistinctOrder();
//
        println(String.format("耗时[%s]毫秒", (System.currentTimeMillis() - start)));

//        group();
//        stord();
//        limit();
//        skip();
//        statistics();
//        summarizingInt();
//        toSet();
//        toMap();
        map();
//        anyMatch();
        reduce();
    }

    /**
     * 多条件去重：1、利用TreeSet的Comparator构造器方法，构造不重复的条件；2、利用Function的R apply(T t)转换为ArrayList;
     * 3、使用Collectors.collectingAndThen完成最终转换
     *
     * @param list
     */
    public static void multiConditionDistinctOrder() {
        ArrayList<User> userList = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(o -> o.getAge() + ";" + o.getName()))), ArrayList::new));
        userList.forEach(u -> println(u));
    }

    public static void group() {
        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(collect.get(10));
    }

    /**
     * filter过滤
     *
     * @param list
     */
    public static void filterAge() {
        list.stream().filter(u -> u.getAge() == 10).forEach(u -> println(u));
    }

    /**
     * sorted排序
     */
    public static void stord() {
        list.stream().sorted(Comparator.comparing(u -> u.getAge())).forEach(u -> println(u));

    }

    /**
     * limit方法限制最多返回多少元素
     */
    public static void limit() {
        list.stream().limit(2).forEach(u -> println(u));
    }

    /**
     * 不要前多n个元素，n大于满足条件的元素个数就返回空的流
     */
    public static void skip() {
        list.stream().skip(2).forEach(u -> println(u));
    }

    // 最大值 最小值
    public static void statistics() {
        Optional<User> min = list.stream().min(Comparator.comparing(User::getUserId));
        println(min);
        Optional<User> max = list.stream().max(Comparator.comparing(User::getUserId));
        println(max);
    }

    // 统计
    public static void summarizingInt() {
        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(User::getAge));
        double average = statistics.getAverage();
        long count = statistics.getCount();
        int max = statistics.getMax();
        int min = statistics.getMin();
        long sum = statistics.getSum();
        println(average);
        println(count);
        println(max);
        println(min);
        println(sum);

    }

    /**
     * 转set
     */
    public static void toSet() {
        Set<User> collect = list.stream().collect(Collectors.toSet());
        Iterator<User> iterator = collect.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getUserId());
        }
    }

    /**
     * 转map
     */
    public static void toMap() {
        Map<Integer, User> collect = list.stream().collect(Collectors.toMap(User::getUserId, u -> u));
        for (Integer in : collect.keySet()) {
            User u = collect.get(in);//得到每个key多对用value的值
            println(u);
        }
    }

    /**
     * map
     */
    public static void map() {
        list.stream().map(User::getUserId).forEach(userId -> println(userId));
        list.stream().mapToInt(User::getAge).forEach(userId -> println(userId));
        list.stream().mapToDouble(User::getUserId).forEach(userId -> println(userId));
        list.stream().mapToLong(User::getUserId).forEach(userId -> println(userId));
    }

    /**
     * 查找与匹配
     * allMatch方法与anyMatch差不多，表示所有的元素都满足才返回true。noneMatch方法表示没有元素满足
     */
    public static void anyMatch() {
        boolean anyMatch = list.stream().anyMatch(u -> u.getAge() == 100);
        boolean allMatch = list.stream().allMatch(u -> u.getUserId() == 10);
        boolean noneMatch = list.stream().noneMatch(u -> u.getUserId() == 10);
        println(anyMatch);
        println(allMatch);
        println(noneMatch);
    }

    /**
     * reduce操作
     */
    public static void reduce() {
        Optional<Integer> sum = list.stream().map(User::getAge).reduce(Integer::sum);
        Optional<Integer> max = list.stream().map(User::getAge).reduce(Integer::max);
        Optional<Integer> min = list.stream().map(User::getAge).reduce(Integer::min);
        println(sum);
        println(max);
        println(min);
    }

    /**
     * 公共输出
     *
     * @param c
     */
    public static void println(Object c) {
        System.out.println(c.toString());
    }


    static class User {
        private Integer userId;

        private String name;

        private Integer age;

        User(Integer userId, String name, Integer age) {
            super();
            this.userId = userId;
            this.name = name;
            this.age = age;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "[userId=" + userId + ", name=" + name + ", age=" + age + "]";
        }

    }
}

