package com.util;


import com.pojo.StudentComparable;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    @Test
    public void array_test() {
        int intArray[] = new int[1];
        System.out.println(intArray[0]);
        Map<String, Object> fileMap = new HashMap<>();
        List<String> idList = new ArrayList<>();
        idList.add("1");
        idList.add("2");
        fileMap.put("customFiles", idList);
        idList.contains("1");
        List<String> customFileIdList = (List<String>) fileMap.get("customFiles");
        System.out.println(customFileIdList);
    }

    //创建map，循环向list中新增map
    @Test
    public void test4() {
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

        List<String> ids = mapList.stream().map(m -> m.get("id").equals("2") ? "2" : null).collect(Collectors.toList());
        System.out.println("ids：" + ids);
    }

    @Test
    public void testCycleRemoveMapOrList() {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("name", "jack");
                put("age", 20);
                put(null, null);
            }
        };
        //ConcurrentModificationException
//        map.entrySet().forEach(m -> {
//            if (Objects.equals("name", m.getKey())) {
//                map.remove(m.getKey());
//            }
//        });
        //ConcurrentModificationException);
//        for (Map.Entry<String, Object> m : map.entrySet()) {
//            if (Objects.equals("name", m.getKey())) {
//                map.remove(m.getKey());
//            }
//        }
        //有效
//        map.remove("name");
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        map.entrySet().forEach(m -> System.out.println(m.getKey() + ":" + m.getValue()));
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        entries.iterator().next();

        List<String> strList1 = new ArrayList<String>() {{
            add("1");
            add("2");
            add("1");
            add("2");
        }};
        List<String> strList2 = new ArrayList<String>() {{
            add("1");
            add(null);
            add("2");
            add("1");
            add("2");
            add(null);
            add("2");
        }};
        strList1.remove("1");//只会remove第一个
        System.out.println("strList1===" + strList1);
        strList2.removeAll(Collections.singleton("1"));//remove所有
        System.out.println("strList2 remore '1'===" + strList2);
        strList2.removeAll(Collections.singleton(null));//remove null
        System.out.println("strList2 remore 'null'===" + strList2);
    }

    @Test
    public void testLinkedList() {
        List<Integer> linkedList = new LinkedList<Integer>() {{
            add(6);
            add(5);
            add(1);
            add(2);
        }};
        System.out.println(linkedList);
    }

    //Map测试
    @Test
    public void testMap() {
        //HashMap的get()使用key的equals方法取出相应value,重写key的equals方法即可取出key中对应value
        Map<StudentComparable, Integer> hashMap = new HashMap<>();
        assemblyMap(hashMap);
        System.out.println(hashMap.get(new StudentComparable("Michael", 99)));
        System.out.println(hashMap.get(new StudentComparable("Bob", 88)));
        System.out.println(hashMap.get(new StudentComparable("Alice", 77)));
        //TreeMap的get()使用key的compareTo方法取出相应map,如compareTo相等即可取出key中对应value
        TreeMap<StudentComparable, Integer> treeMap = new TreeMap<>();
        assemblyMap(treeMap);
        System.out.println(treeMap.get(new StudentComparable("Michael", 99)));
        System.out.println(treeMap.get(new StudentComparable("Bob", 88)));
        System.out.println(treeMap.get(new StudentComparable("Alice", 77)));
        //排序
        System.out.println("treeMap sort======");
        Collections.synchronizedSortedMap(treeMap).entrySet().forEach(t -> System.out.println(t.getValue()));
    }

    //组装map
    public void assemblyMap(Map<StudentComparable, Integer> map) {
        map.put(new StudentComparable("Michael", 99), 99);
        map.put(new StudentComparable("Bob", 88), 88);
        map.put(new StudentComparable("Alice", 77), 77);
    }

    @Test
    public void testList() {
        //排序
        System.out.println("List sort======");
        List<StudentComparable> studentComparableList = new ArrayList<>();
        assemblyList(studentComparableList);
        Collections.sort(studentComparableList, (o1, o2) -> o2.getScore() - o1.getScore());
        studentComparableList.forEach(s -> System.out.println(s.getScore()));
    }

    private void assemblyList(List<StudentComparable> studentComparableList) {
        studentComparableList.add(new StudentComparable("Bob", 88));
        studentComparableList.add(new StudentComparable("Michael", 99));
        studentComparableList.add(new StudentComparable("Alice", 77));
    }

    @Test
    public void collectionsApi() {
        //CollectionUtils
        List<String> strList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        System.out.println("Collection isEmpty===" + CollectionUtils.isEmpty(strList));
        System.out.println("Map isEmpty===" + CollectionUtils.isEmpty(map));
    }

    @Test
    public void testHashMapApi() {
        //自定义扩容
        HashMap<Object, Object> map = new HashMap<>(16);
        System.out.println("两倍幂：" + tableSizeFor(10));
    }

    /**
     * 返回给定目标容量的两倍幂(来自于HashMap)
     */
    static final int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public long testBigData() {
        long time1 = System.currentTimeMillis();
        long total = 0;
        for (int i = 1; i < 1000000; i++) {
            total += total;
        }
//        System.out.println(String.format("total=%s,花费时间=%s", total, (System.currentTimeMillis() - time1)));
        System.out.println("total=" + total + ";花费时间=" + (System.currentTimeMillis() - time1));
        return total;
    }

    @Test
    public void testThreadLocalMapHash() {
        int HASH_INCREMENT = 0x61c88647;
        int INITIAL_CAPACITY = 16;
        AtomicInteger nextHashCode = new AtomicInteger();
        int threadLocalHashCode = nextHashCode.addAndGet(HASH_INCREMENT);
        int i = threadLocalHashCode & (INITIAL_CAPACITY - 1);
        System.out.println(i);
    }

    @Test
    public void testMapEquals() {
        HashMap<String, String> map1 = new HashMap<String, String>() {{
            put("name", "jack");
            put("like", "game");
        }};
        HashMap<String, String> map2 = new HashMap<String, String>() {{
            put("name", "jack");
            put("like", "game");
        }};
        System.out.println(map1.equals(map2));
    }
}
