package com.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xsy
 * @create 2019-08-20 18:31
 * @desc Arrays测试
 **/
public class ArraysTest {
    public static void main(String[] args) {
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        Integer four = new Integer(4);
        List<Integer> intList = new ArrayList<>(Arrays.asList(one, two, three, four));
        List<Integer> intList2 = Arrays.asList(new Integer[]{one, two, three, four});
        System.out.println(intList.get(0));
        System.out.println(intList.get(0) == one);
        System.out.println(intList2.get(0) == one);
        intList.add(5);
//        intList2.add(5); //抛出UnsupportedOperationException，因为Arrays.ArrayList中没有重写add()方法
    }
}
