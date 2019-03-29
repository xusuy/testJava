package com.util;


import org.junit.jupiter.api.Test;

import java.util.*;

/***
 * 集合
 */
public class CollectionUtil {
    public static void main(String[] args) {
        String str_array[] = new String[]{"1", "2", "3"};
        List<String> stringList = Arrays.asList(str_array);
//        stringList.add("4");  抛出UnsupportedOperationException，因为Arrays.ArrayList中没有重写add()方法
        str_array[0] = "2";
        System.out.println(stringList.get(0));
        System.out.println(stringList.size());
    }

    @Test
    public void mapEntryTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("name", "jack");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        map.get("id");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 123);
        System.out.println(map.equals(map2));
    }

    @Test
    public void listTest() {
        new ArrayList<>().add("2");
    }

    @Test
    public void mapSizeTest() {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < 1000000; i++) {
            map.put(i + 1, 6);
        }
        System.out.println(map.size());
    }

}
