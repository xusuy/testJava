package com.basic;

import org.junit.Test;

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

    @Test
    public void test1() {
        //数组的第一维大小必须初始化
        int[] odArray = new int[2];
        int[][] tdArray1 = new int[2][];
        int tdArray2[][] = new int[2][];
        int[] tdArray3[] = new int[2][];
        tdArray3[0] = new int[6];
        tdArray3[1] = new int[8];
        tdArray3[0][1] = 111;
        tdArray3[0][5] = 222;
        tdArray3[1][7] = 333;
    }
}
