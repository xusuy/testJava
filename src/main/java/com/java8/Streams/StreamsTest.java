package com.java8.Streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
