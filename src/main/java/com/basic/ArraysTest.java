package com.basic;

import org.junit.Test;

import java.lang.reflect.Array;
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
        List<Integer> intList2 = Arrays.asList(one, two, three, four);
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

    @Test
    public void testArray() {
        Class clazz = String.class;

        //创建一个长度为10的字符串数组，在Java中数组也可以作为Object对象
        Object array = Array.newInstance(clazz, 10);

        //把字符串数组对象的索引位置为5的元素设置为"hello"
        Array.set(array, 5, "hello");

        //获得字符串数组对象的索引位置为5的元素的值
        String str = (String) Array.get(array, 5);
        System.out.println(str);//hello
    }

    /**
     * 动态修改数组(Array)的大小
     *
     * @param oldArray 老数组
     * @param newSize  新长度
     * @return
     */
    private static Object resizeArray(Object oldArray, int newSize) {
        //老数组的长度
        int oldSize = Array.getLength(oldArray);
        //数组类型
        Class elementType = oldArray.getClass().getComponentType();
        //一个新的数组 类型和oldArray的一样 长度newSize
        //Array.newInstance返回的是elementType的数组，可以通过类型转换为elementType数组
        Object newArray = Array.newInstance(
                elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }
}
