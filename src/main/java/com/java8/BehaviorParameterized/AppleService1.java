package com.java8.BehaviorParameterized;

import com.domain.Apple;
import com.domain.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xsy
 * @create 2019-08-16 16:54
 * @desc 业务类1
 **/
public class AppleService1 {

    // 过滤器的筛选方法(行为参数化：让函数仅保留模板代码，而把筛选条件抽离出来当做参数传递进来)
    public static List<Apple> getFilterApplesByAppleFilter(List<Apple> sourceApples, AppleFilter appleFilter) {
        List<Apple> newApples = new ArrayList<>();
        for (Apple sourceApple : sourceApples) {
            if (appleFilter.isAccept(sourceApple)) {
                newApples.add(sourceApple);
            }
        }
        return newApples;
    }

    public static void main(String[] args) {
        List<Apple> sourceApples = new ArrayList<Apple>() {{
            add(new Apple(1l, Color.RED, 6.0f, "1"));
            add(new Apple(2l, Color.BLUE, 2.0f, "1"));
            add(new Apple(3l, Color.GREEN, 3.0f, "1"));
        }};
        //java8以前的写法——匿名内部类
        List<Apple> newApples1 = getFilterApplesByAppleFilter(sourceApples, new AppleFilter() {
            @Override
            public boolean isAccept(Apple apple) {
                return apple.getColor().equals(Color.RED) && apple.getWeight() > 5;
            }
        });
        //java8——行为参数化
        List<Apple> newApples2 = getFilterApplesByAppleFilter(sourceApples, apple -> apple.getColor().equals(Color.RED) && apple.getWeight() > 5);
        System.out.println("Apple result by AppleFilter===" + newApples2);
    }
}
