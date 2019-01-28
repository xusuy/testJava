package com.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xsy
 * @create 2018-11-20 14:07
 * @desc 支持并行数组处理：最重要的方法是parallelSort()，显著加快多核机器上的数组排序
 **/
public class ParallelArrays {
    public static void main(String[] args) {
        long[] arrayOfLong = new long[20000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println("end==" + args[0]);
    }
}
