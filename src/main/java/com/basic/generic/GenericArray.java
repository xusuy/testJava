package com.basic.generic;

import java.lang.reflect.Array;

public class GenericArray<T> {
    private T[] array;

    //错误创建数组方式
    @SuppressWarnings("unchecked")
    public GenericArray(int size) {
        array = (T[]) new Object[size];
    }

    //正确创建数组方式
    @SuppressWarnings("unchecked")
    public GenericArray(Class arrayClass, int size) {
        array = (T[]) Array.newInstance(arrayClass, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] getArray() {
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> genericArray = new GenericArray<>(10);
        genericArray.put(0, 661);
        genericArray.put(1, 662);
        System.out.println(genericArray.get(0));
        System.out.println(genericArray.get(1));
        //ClassCastException
//        Integer[] intArray = genericArray.getArray();
        System.out.println(genericArray.getClass().getSimpleName());
        Object[] objArray = genericArray.getArray();

        GenericArray<Integer> genericArray2 = new GenericArray<>(Integer.class, 10);
        //不会产生异常
        Integer[] intArray = genericArray2.getArray();
    }
}
