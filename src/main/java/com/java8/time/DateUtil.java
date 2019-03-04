package com.java8.time;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author xsy
 * @create 2019-03-01 16:40
 * @desc
 **/
public class DateUtil {
    public static void main(String[] args) {
        //LocalDate不包含时间，只是单纯的年月日，要精确到时分秒甚至毫秒的就需要用java.time.LocalDateTime。
//获取点当前时间对象

        //Date转LocalDateTime
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        //LocalDateTime转Date
        Instant instant2 = localDateTime.atZone(zone).toInstant();
        Date date2 = Date.from(instant);


        LocalDate now = LocalDate.now();

//今年是哪一年
        int dayofYear = now.getDayOfYear();

//今天是哪一月
        int month = now.getMonth().getValue();

// 今天是几号
        int dayofMonth = now.getDayOfMonth();

// 今天是周几
        int dayofWeek = now.getDayOfWeek().getValue();

//设置指定日期的时间对象:
        LocalDate appoint = LocalDate.parse("2018-12-07");

//计算两个日期相差多少天:
        long differ = appoint.toEpochDay() - now.toEpochDay();

//指定天数的加减
        LocalDate minus = now.minusDays(10);
        System.out.println(minus);
        LocalDate plus = now.plusDays(10);
        System.out.println(plus);

//校验两个日期的前后关系
        boolean flag = now.isBefore(appoint);
        System.out.println(flag);

// 获取取本月第1天：
        LocalDate firstDayOfThisMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfThisMonth = " + firstDayOfThisMonth);

// 取本月第2天：
        LocalDate secondDayOfThisMonth = now.withDayOfMonth(2);
        System.out.println("secondDayOfThisMonth = " + secondDayOfThisMonth);

// 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfThisMonth = " + lastDayOfThisMonth);

// 取下月第一天：
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        System.out.println("firstDayOfNextMonth = " + firstDayOfNextMonth);

// 取下月最后一天：
        LocalDate lastDayOfNextOfMonth = firstDayOfNextMonth.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfNextOfMonth = " + lastDayOfNextOfMonth);

// 取2019年1月第一个周一，用Calendar要死掉很多脑细胞：
        LocalDate special = LocalDate.parse("2019-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("special = " + special);
    }

}
