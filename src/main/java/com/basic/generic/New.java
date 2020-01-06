package com.basic.generic;

import java.util.*;

/**
 * 利用泛型创建容器类
 */
public class New {
    public static <k, v> Map<k, v> hashMap() {
        return new HashMap<>();
    }

    public static <T> List<T> arrayList() {
        return new ArrayList<T>();
    }

    public static <T> LinkedList<T> linkedList() {
        return new LinkedList<T>();
    }

    public static <E> Set<E> hashSet() {
        return new HashSet<E>();
    }

    public static <E> Queue<E> queue() {
        return new LinkedList<E>();
    }

    public static void main(String[] args) {
        Map<String, List<String>> hashMap = New.hashMap();
        List<String> stringArrayList = New.arrayList();
        LinkedList<String> stringLinkList = New.linkedList();
        Set<String> hasSet = New.hashSet();
        Queue<String> queue = New.queue();
    }
}
