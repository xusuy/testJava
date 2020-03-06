package com.java8.Streams;

import com.pojo.Anxinmanagcommunitygoods;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xsy
 * @create 2018-11-20 13:33
 * @desc:Streams测试类
 **/
public class StreamsTest {
    final Collection<Task> tasks = Arrays.asList(
            new Task(StatusEnum.OPEN, 5),
            new Task(StatusEnum.OPEN, 13),
            new Task(StatusEnum.CLOSED, 8)
    );

    @Test
    public void test() {
        //长度小于5的字符串个数
        List<String> list = Arrays.asList("java", "scala", "python", "shell", "ruby", "scala");
        long num = list.stream().filter(x -> x.length() < 5).count();
        System.out.println(num);
        //添加前缀和后缀
        String joinStr = list.stream().collect(Collectors.joining("', '", "'", "'"));
        System.out.println("添加前缀和后缀==" + joinStr);
        //排序
        Stream<Integer> sortedReverseStreamV2 = Stream.of(1, 3, 7, 4, 5, 8, 6, 2).sorted((o1, o2) -> o2 - o1);
        List<Integer> integerList = sortedReverseStreamV2.collect(Collectors.toList());
        integerList.forEach(x -> System.out.print(x + " "));
        System.out.println();
        Integer[] intArray = {1, 3, 7, 4, 5, 8, 6, 2};
        Stream.of(intArray);
        //distinct去重
        List<Object> distinctList = Stream.of(list.toArray()).distinct().collect(Collectors.toList());
        System.out.println("list distinct去重==" + distinctList);
        List<String> strList = Stream.of("bj", "shanghai", "tianjin", "bj", "shanghai").distinct()
                .sorted(Comparator.comparing(String::length))
                .collect((Collectors.toList()));
        System.out.println("distinctStream sorted==" + strList);

        // 使用sum()计算相应活动任务的总分
        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter(task -> task.getStatus() == StatusEnum.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // 使用sum()计算所有活动任务的总分
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints()) // or map( Task::getPoints )
                .reduce(0, Integer::sum);

        System.out.println("Total points (all tasks): " + totalPoints);
        // 分组计算
        final Map<StatusEnum, List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        // 计算集合中每个任务的点数在集合中所占的比重
        final Collection<String> result = tasks
                .stream()                                        // Stream< String >
                .mapToInt(Task::getPoints)                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble(points -> points / totalPoints)   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%")      // Stream< String>
                .collect(Collectors.toList());                 // List< String >

        System.out.println(result);

    }

    @Test
    public void test2() {
        //to List
        List<Anxinmanagcommunitygoods> list = new ArrayList<>();
        Anxinmanagcommunitygoods goods = new Anxinmanagcommunitygoods();
        goods.setGoodsName("1");
        list.add(goods);
        goods = new Anxinmanagcommunitygoods();
        goods.setGoodsName("2");
        list.add(goods);
        goods = new Anxinmanagcommunitygoods();
        goods.setGoodsName("3");
        list.add(goods);
        String join = String.join(",", list.stream().map(item -> item.getGoodsName()).collect(Collectors.toList()));
        System.out.println("join===" + join);
        List<String> strList = new ArrayList<>();
        strList.addAll(list.stream().map
                (item -> item.getGoodsName())
                .collect(Collectors.toList()));
        //filter
        Anxinmanagcommunitygoods anxinmanagcommunitygoods = list.stream()
                .filter(goo -> Objects.equals("2", goo.getGoodsName()))
                .findFirst()
//                .findAny()
                .orElse(null);
        System.out.println("orElse===" + anxinmanagcommunitygoods);

        Optional<Anxinmanagcommunitygoods> goodsOptional = list.stream()
                .filter(goo -> Objects.equals("2", goo.getGoodsName()))
                .findAny();
        if (goodsOptional.isPresent()) {
            System.out.println("Optional===" + goodsOptional.get());
        }

        List<Anxinmanagcommunitygoods> filterList = list.stream()
                .filter(go -> !Objects.equals("3", go.getGoodsName()))
                .collect(Collectors.toList());
        System.out.println("filter to List===" + filterList);

    }
}
