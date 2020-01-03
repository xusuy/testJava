package com.java8.Streams;

import com.util.MathTimeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xsy
 * @create 2019-11-04 10:56
 * @desc List<Map<String, Object>> 排序测试
 **/
public class OrderByTest {

    private static List<Map<String, Object>> mapList = new ArrayList<>();

    static {
        Date date1 = new Date();
        Date date2 = MathTimeUtil.laterDate(date1, 1);
        Date date3 = MathTimeUtil.laterDate(date2, 1);
        Date date4 = MathTimeUtil.laterDate(date3, 1);
        Map<String, Object> map1 = new HashMap<String, Object>() {{
            put("id", 1);
            put("name", "jack");
            put("checkDate", date2);
        }};
        Map<String, Object> map2 = new HashMap<String, Object>() {{
            put("id", 2);
            put("name", "rose");
            put("checkDate", date1);
        }};
        Map<String, Object> map3 = new HashMap<String, Object>() {{
            put("id", 3);
            put("name", "make");
            put("checkDate", date4);
        }};
        Map<String, Object> map4 = new HashMap<String, Object>() {{
            put("id", 4);
            put("name", "make");
            put("checkDate", date3);
        }};
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
    }

    public static void main(String[] args) {
        System.out.println("排序前===" + mapList);
        //checkDate不能为空
        //1、自己构建排序comparator
        List<Map<String, Object>> mapList2 = mapList.stream().sorted((o1, o2) -> {
            Date date1 = (Date) o1.get("checkDate");
            Date date2 = (Date) o2.get("checkDate");
            return date1.compareTo(date2);
        }).collect(Collectors.toList());
        System.out.println("排序后1===" + mapList2);

        //2、使用Comparator.comparing构建comparator排序
        List<Map<String, Object>> mapList3 = mapList.stream().sorted(
                Comparator.comparing(
                        map -> (Date) map.get("checkDate"))).collect(Collectors.toList());
        System.out.println("排序后2===" + mapList3);
    }
}
