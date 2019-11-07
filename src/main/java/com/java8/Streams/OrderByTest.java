package com.java8.Streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xsy
 * @create 2019-11-04 10:56
 * @desc 排序测试
 **/
public class OrderByTest {

    private static List<Map<String, Object>> mapList = new ArrayList<>();

    static {
        Map<String, Object> map1 = new HashMap<String, Object>() {{
            put("id", 1);
            put("name", "jack");
            put("checkDate", "2019-11-3");
        }};
        Map<String, Object> map2 = new HashMap<String, Object>() {{
            put("id", 2);
            put("name", "rose");
            put("checkDate", "2019-11-2");
        }};
        Map<String, Object> map3 = new HashMap<String, Object>() {{
            put("id", 3);
            put("name", "make");
            put("checkDate", "2019-11-5");
        }};
        Map<String, Object> map4 = new HashMap<String, Object>() {{
            put("id", 3);
            put("name", "make");
            put("checkDate", null);
        }};
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
    }

    public static void main(String[] args) {
        System.out.println("排序前===" + mapList);
        List<Map<String, Object>> mapList2 = mapList.stream().sorted(Comparator.comparing(map -> map.get("checkDate") + "")).collect(Collectors.toList());
        System.out.println("排序后===" + mapList2);
    }
}
