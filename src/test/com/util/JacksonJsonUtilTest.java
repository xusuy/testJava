package com.util;

import com.domain.CarInfo;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JacksonJsonUtilTest {

    @Test
    public void test() {
        //json转实体
        String car_json = "{\"name\":\"test\",\"code\":\"001\"}";
        CarInfo carInfo = JacksonJsonUtil.readValue(car_json, CarInfo.class);
        System.out.println(String.format("转化为实体bean:\n%s", carInfo));
        carInfo.setCarOwer("大王");
        System.out.println("javabean转化为json:\n" + JacksonJsonUtil.toJSon(carInfo));

        Map map = JacksonJsonUtil.readValue(car_json, Map.class);
        System.out.println("转化为map:\n" + map);

        //json转实体数组方法1
        String carlist_josn = "[{\"name\":\"test1\",\"code\":\"001\"},{\"name\":\"test2\",\"code\":\"002\"},{\"name\":\"test3\",\"code\":\"003\"}]";
        List<CarInfo> carInfoList = Arrays.asList(JacksonJsonUtil.readValue(carlist_josn, CarInfo[].class));
        System.out.println("转化为实体list:\n" + carInfoList);
        System.out.println("javabean转化为json array:\n" + JacksonJsonUtil.toJSon(carInfoList));
        //json转实体数组方法2
        carInfoList = JacksonJsonUtil.readValue(carlist_josn, new TypeReference<List<CarInfo>>() {
        });
        System.out.println("转化为实体list:\n" + carInfoList);
        System.out.println("javabean转化为json array:\n" + JacksonJsonUtil.toJSon(carInfoList));
    }

}