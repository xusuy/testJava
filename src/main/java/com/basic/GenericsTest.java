package com.basic;

import com.java8.Car;
import com.java8.CarTest;

import java.lang.reflect.Array;

/**
 * @author xsy
 * @create 2017-02-26 10:04
 * @desc 泛形
 **/
public class GenericsTest {
    /**
     * 接收一个泛型数组，然后创建一个长度与接收的数组长度一样的泛型数组，
     * 并把接收的数组的元素复制到新创建的数组中，
     * 最后找出新数组中的最小元素，并打印出来
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<T>> void getMinFromArray(T[] a) {
        //通过反射创建相同类型的数组
        T[] b = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        T min = null;
        boolean flag = true;
        for (int i = 0; i < b.length; i++) {
            if (flag) {
                min = b[i];
                flag = false;
            }
            if (b[i].compareTo(min) < 0) {
                min = b[i];
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) {
        //编译通过！
        Class<? extends Number> clazz = Integer.class;
        //赋予其他类型
        clazz = double.class;
        clazz = Number.class;

        Integer inaArray[] = {1, 4, 7, 2, 8};
        getMinFromArray(inaArray);
        String strArray[] = {"3", "5", "1", "0"};
        getMinFromArray(strArray);

        Car car = new Car();
        Object object = new Object();
//        object = car;
//        car = (Car) object;//ClassCastException
        if (object instanceof Car) {
            car = (Car) object;
        }
    }

}
