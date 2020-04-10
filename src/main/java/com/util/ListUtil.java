package com.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xsy
 * @create 2020-04-08 16:26
 * @desc
 **/
public class ListUtil {
    //重组文件名：如有多个同样的文件名，则添加后缀_1,_2...(bug:不能在同名文件中已存在有后缀的文件)
    private List<Map<String, String>> restructuringFileName(List<Map<String, String>> fileList) {
        List<Map<String, String>> newList = new ArrayList<>();
        Map<String, Integer> statisticsMap = new HashMap<>();
        fileList.forEach(fileMap -> {
            String fileName = fileMap.get("fileName");
            String url = fileMap.get("url");
            Map<String, String> newMap = new HashMap<>();
            newMap.put("url", url);
            int lastIndexOf = fileName.lastIndexOf(".");
            int sameCount = (int) fileList.stream().filter(f -> fileName.equals(f.get("fileName"))).count();
            if (statisticsMap.get(fileName) == null) {
                if (sameCount > 1) {
                    statisticsMap.put(fileName, sameCount);
                    newMap.put("fileName", fileName.substring(0, lastIndexOf) + "_1" + fileName.substring(lastIndexOf));
                    statisticsMap.put(fileName, sameCount - 1);
                } else {
                    newMap.putAll(fileMap);
                }
            } else {
                int remaindCount = statisticsMap.get(fileName);
                int item = sameCount - remaindCount + 1;
                newMap.put("fileName", fileName.substring(0, lastIndexOf) + "_" + item + fileName.substring(lastIndexOf));
                statisticsMap.put(fileName, remaindCount - 1);
            }
            newList.add(newMap);
        });
        return newList;
    }

    @Test
    public void testRestructuringFileName() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("url", "https//www.aa.jpg");
        map1.put("fileName", "a.jpg");
        Map<String, String> map2 = new HashMap<>();
        map2.put("url", "https//www.bb.jpg");
        map2.put("fileName", "b.jpg");
        Map<String, String> map3 = new HashMap<>();
        map3.put("url", "https//www.c.jpg");
        map3.put("fileName", "c.jpg");
        Map<String, String> map4 = new HashMap<>();
        map4.put("url", "https//www.ab.jpg");
        map4.put("fileName", "a.jpg");
        Map<String, String> map5 = new HashMap<>();
        map5.put("url", "https//www.bc.jpg");
        map5.put("fileName", "b.jpg");
        Map<String, String> map6 = new HashMap<>();
        map6.put("url", "https//www.e.jpg");
        map6.put("fileName", "e.jpg");
        Map<String, String> map7 = new HashMap<>();
        map7.put("url", "https//www.ac.jpg");
        map7.put("fileName", "a__2.jpg");
        List<Map<String, String>> fileList = new ArrayList<>();
        fileList.add(map1);
        fileList.add(map2);
        fileList.add(map3);
        fileList.add(map4);
        fileList.add(map5);
        fileList.add(map6);
        fileList.add(map7);
        System.out.println(restructuringFileName(fileList));
    }
}
