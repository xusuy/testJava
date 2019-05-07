package com.util;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void test1() {
        List<String> strings = new ArrayList<String>();
        unsafeAdd1(strings, new Integer(42));
        String s = strings.get(0);//java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        System.out.println(s);
    }

    public static void unsafeAdd1(List list, Object o) {
        list.add(o);
    }

    public void test2() {
        List<String> strings = new ArrayList<String>();
//        unsafeAdd2(strings, new Integer(42));//编译不通过
//        String s = strings.get(0);
//        System.out.println(s);
    }

    public static void unsafeAdd2(List<Object> list, Object o) {
        list.add(o);
    }

    @Test
    public void mapSizeTest() {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < 1000000; i++) {
            map.put(i + 1, 6);
        }
        System.out.println(map.size());
    }

    //创建map，循环向list中新增map
    @Test
    public void test3() {
        Map<String, String> map1 = new HashMap<String, String>() {
            {
                put("id", "1");
                put("age", "20");
            }
        };
        Map<String, String> map2 = new HashMap<String, String>() {
            {
                put("id", "2");
                put("age", "25");
            }
        };
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>() {
            {
                add(map1);
                add(map2);
            }
        };
        System.out.println(mapList);
        mapList.forEach(m -> {
            String name = Objects.equals("1", m.get("id")) ? "66" : "77";
            m.put("name", name);
        });
        System.out.println(mapList);
    }

}
