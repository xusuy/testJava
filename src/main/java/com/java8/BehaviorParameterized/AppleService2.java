package com.java8.BehaviorParameterized;

import com.domain.Apple;
import com.domain.Color;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author xsy
 * @create 2019-08-20 9:31
 * @desc 业务类2
 **/
public class AppleService2 {

    static List<Apple> sourceApples;

    static {
        sourceApples = new ArrayList<Apple>() {{
            add(new Apple(1l, Color.RED, 6.0f, "1"));
            add(new Apple(2l, Color.BLUE, 2.0f, "1"));
            add(new Apple(3l, Color.GREEN, 3.0f, "1"));
        }};
    }

    //利用Predicate<T>：利用我们在外部设定的条件对于传入的参数进行校验并返回验证通过与否
    public static <T> List<T> getFilterApplesByPredicate(List<T> list, Predicate<T> predicate) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                iterator.remove();
            }
        }
        return list;
    }

    @Test
    public void test1() {
        //使用原生Predicate<T>解决筛选Apple
        System.out.println("Apple result by Predicate<T>===" + getFilterApplesByPredicate(sourceApples, apple -> Color.RED.equals(apple.getColor()) && apple.getWeight() > 5));
        //使用原生Predicate<T>解决筛选偶数
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(getFilterApplesByPredicate(intList, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        }));
    }

    //测试排序
    @Test
    public void test2() {
        //使用lambda
        sourceApples.sort((o1, o2) -> Float.compare(o1.getWeight(), o2.getWeight()));
        System.out.println("sourceApples lambda sort===" + sourceApples);
        //使用方法引用
        sourceApples.sort(Comparator.comparing(Apple::getWeight));
        //内部类形式
        sourceApples.sort(Comparator.comparing(new Function<Apple, Float>() {
            @Override
            public Float apply(Apple apple) {
                return apple.getWeight();
            }
        }));
        System.out.println("sourceApples 方法引用 sort===" + sourceApples);
    }
}
