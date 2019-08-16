package com.java8.BehaviorParameterized;

import com.domain.Apple;

/**
 * @author xsy
 * @create 2019-08-16 16:52
 * @desc 过滤器
 **/
@FunctionalInterface//表名是函数式接口：仅含有一个抽象方法的接口。（不是必选注释）
public interface AppleFilter {
    boolean isAccept(Apple apple);
}
