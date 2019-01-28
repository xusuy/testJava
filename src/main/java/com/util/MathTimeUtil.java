package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MathTimeUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat sdf_yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf_yyyymmdd_spot = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat sdf_yyyymmddhhmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat sdf_yyyymmddhhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf_yyyymmddhhmmss_short = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdfyyyymmddhhmm = new SimpleDateFormat("yyyyMMddHHmm");
    private static final SimpleDateFormat sdf_yyyyMMdd_short = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat sdf_yyyymm = new SimpleDateFormat("yyyy-MM");

    /**
     * @param sendTime 验证码发送时间
     * @param x        间隔时间分钟
     * @return
     * @author xsy 2017年3月28日
     */
    public static boolean isTrue(Date sendTime, int x) {
        int a = 0;
        Date today = new Date();
        // 60*1000 一分钟
        if (x <= 0) {
            x = 1;
        }
        x = x * 60 * 1000;
        if ((today.getTime() - sendTime.getTime()) > 0
                && (today.getTime() - sendTime.getTime()) <= x) {
            a = 1;
        }
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param oldTime
     * @param newTime
     * @param x       秒
     * @return
     * @author zhaofh 2016年10月27日
     */
    public static boolean betweenTrue(Date oldTime, Date newTime, int x) {
        x = x <= 0 ? (1000) : (x * 1000);
        if ((newTime.getTime() - oldTime.getTime()) > 0
                && (newTime.getTime() - oldTime.getTime()) <= x) {
            return true;
        }
        return false;
    }

    /**
     * 几年后
     *
     * @param mathTime  计算时间
     * @param lateryear 几年后
     * @return
     * @throws Exception
     * @author zhaofh 2017年4月6日
     */
    public static Date getYearDate(Date mathTime, int lateryear) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mathTime);
            calendar.add(Calendar.YEAR, lateryear);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 几个月后
     *
     * @param mathTime
     * @param latermonth
     * @return
     * @throws Exception
     * @author zhaofh 2017年4月10日
     */
    public static Date getMonthDate(Date mathTime, int latermonth) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mathTime);
            calendar.add(Calendar.MONTH, latermonth);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 小于等于当前时间 yyyy-MM-dd
     *
     * @param mathTime
     * @return
     * @author zhaofh 2017年4月6日
     */
    public static boolean isSmallNowYMD(Date mathTime) {
        try {
            Date today = stringParseDateForYMD(dateParseStringForYMD(new Date()));
            Date mathDate = stringParseDateForYMD(dateParseStringForYMD(mathTime));
            if (today.getTime() - mathDate.getTime() >= 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static long betweenDay(Date startDay, Date endDay) {
        long diff = endDay.getTime() - startDay.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }


    /**
     * @param appiontTime     yyyy-MM-dd
     * @param appiontmentTime ss:hh
     * @return
     * @throws ParseException
     */
    public static String groupTime(String appiontTime, String appiontmentTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day = sdf.parse(appiontTime);
        String[] arg = appiontmentTime.split("\\:");
        Calendar canlandar = Calendar.getInstance();
        canlandar.setTime(day);
        canlandar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arg[0]));
        canlandar.set(Calendar.MINUTE, Integer.parseInt(arg[1]));
        canlandar.set(Calendar.SECOND, 0);
        String date = sdfV.format(canlandar.getTime());
        return date;
    }

    public static boolean isInTime(Date date, String limitTime) {
        boolean isInTime = false;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(new Date());
        String[] dateArr = limitTime.split("-");
        try {
            Date currDate = sdf.parse(currentDate);//当前时间
            Date startDate = sdf.parse(dateArr[0]);//每节开始时间
            Date endDate = sdf.parse(dateArr[1]);//每节结束时间
            if (currDate.after(startDate) && currDate.before(endDate)) {
                isInTime = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isInTime;
    }


    /**
     * 当前时间大于某一时间（HH：mm）
     */
    public static boolean greaterThanTime(Date date, String limitTime) {
        boolean isInTime = false;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(date);
        try {
            Date currDate = sdf.parse(currentDate);//当前时间
            Date startDate = sdf.parse(limitTime);//时间节点
            if (currDate.after(startDate)) {
                isInTime = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isInTime;
    }

    /**
     * 算相差月份 date2>date1
     *
     * @param date1
     * @param date2
     * @return
     * @author xsy 2018年6月12日
     */
    public static int getMonthSpace(Date date1, Date date2) {
        int result;
        try {
            result = 0;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(stringParseDateForYMD(dateParseStringForYMD(date1)));
            c2.setTime(stringParseDateForYMD(dateParseStringForYMD(date2)));
            int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

            if (c2.get(Calendar.MONTH) > c1.get(Calendar.MONTH)) {
                result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
                result = Math.abs(result) + year * 12;
            } else {
                result = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
                result = year * 12 - Math.abs(result);
            }
            return result == 0 ? 1 : result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取年检时间
     *
     * @param date
     * @return
     * @throws ParseException
     * @author zhaofh 2017年4月6日
     */
    public static String getBetween(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            Calendar calV = Calendar.getInstance();
            calV.setTime(stringParseDateForYMD(dateParseStringForYMD(date)));
            cal.set(Calendar.YEAR, calV.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calV.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDate = cal.getTime();
            cal.add(Calendar.MONTH, 2);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date lastDate = cal.getTime();
            String result = sdf.format(firstDate).replace("-", "/") + "-" + sdf.format(lastDate).replace("-", "/");
            return result == null || "".equals(result) ? "" : result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换日期为年月日
     *
     * @param date ????-??-??
     * @return ?年?月?日
     * @author zhaofh 2016年8月13日
     */
    public static String getDateForYMD(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf_yyyymmdd.parse(date));
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            return year + "年" + month + "月" + day + "日";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换日期为年月
     *
     * @param date
     * @return
     * @author xsy 2018年6月12日
     */
    public static String getDateForYM(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        return year + "年" + month + "月";
    }

    /**
     * 转换日期为年月日
     *
     * @param date
     * @return
     * @author xsy 2018年6月12日
     */
    public static String getYYRForDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 转换日期为年月日时分
     *
     * @param date：yyyy-mm--dd hh:mm:ss 格式
     * @return
     * @author xsy 2018年6月12日
     */
    public static String getYYRSMForDate(Date date) {
        String dateString = dateParseStringForYMDHMS(date);
        String[] dateStringArray = dateString.split(" ");
        String[] theDateArray = dateStringArray[0].split("\\-");
        String[] theTimeArray = dateStringArray[1].split("\\:");
        String year = theDateArray[0];
        String month = theDateArray[1];
        String day = theDateArray[2];
        String hour = theTimeArray[0];
        String minute = theTimeArray[1];
        return year + "年" + month + "月" + day + "日 " + hour + ":" + minute;
    }

    /**
     * 格式转化 String2date by yyyy-MM-dd
     *
     * @param
     * @return
     * @author zhaofh 2016年8月13日
     */
    public static Date stringParseDateForYMD(String date) {
        try {
            return sdf_yyyymmdd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 式转化 String2date by yyyy-MM
     *
     * @param date
     * @return
     * @author xsy 2018年6月12日
     */
    public static Date stringParseDateForYM(String date) {
        try {
            return sdf_yyyymm.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转化 date2String by yyyy-MM
     *
     * @param date
     * @return
     * @author xsy 2018年6月21日
     */
    public static String dateParseStringForYM(Date date) {
        return sdf_yyyymm.format(date);
    }

    /**
     * 格式转化 date2String by yyyy-MM-dd
     *
     * @param
     * @return
     * @author zhaofh 2016年8月17日
     */
    public static String dateParseStringForYMD(Date date) {
        return sdf_yyyymmdd.format(date);
    }

    /**
     * 格式转化 date2String by yyyy.MM.dd
     *
     * @param
     * @return
     * @author xusy 2016年8月17日
     */
    public static String dateParseStringForYMD_spot(Date date) {
        return sdf_yyyymmdd_spot.format(date);
    }

    /**
     * 格式转化 String2date by yyyy-MM-dd HH:mm
     *
     * @param
     * @return
     * @author zhaofh 2016年8月16日
     */
    public static Date stringParseDateForYMDHM(String date) {
        try {
            return sdf_yyyymmddhhmm.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转化 date2String by yyyy-MM-dd HH:mm
     *
     * @param
     * @return
     * @author zhaofh 2016年8月17日
     */
    public static String dateParseStringForYMDHM(Date date) {
        return sdf_yyyymmddhhmm.format(date);
    }

    /**
     * 格式转化 date2String by yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     * @author xsy 2017年12月8日
     */
    public static String dateParseStringForYMDHMS(Date date) {
        return sdf_yyyymmddhhmmss.format(date);
    }

    /**
     * 格式转化 String2date by yyyy-MM-dd HH:mm:ss
     *
     * @param
     * @return Date
     * @author zhaofh 2016年8月16日
     */
    public static Date stringParseDateForYMDHMS(String date) {
        try {
            return sdf_yyyymmddhhmmss.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转化 String2String  by yyyy-MM-dd HH:mm:ss
     *
     * @param
     * @return Date
     * @author zhaofh 2016年8月16日
     */
    public static String stringParseStringForYMDHMS(String date) {
        try {
            return sdf_yyyymmddhhmmss.format(sdf_yyyymmddhhmmss_short.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转化 Date2String  by yyyyMMddHHmmss
     *
     * @param
     * @return Date
     * @author zhaofh 2016年8月16日
     */
    public static String dateParseStringForYMDHMSS(Date date) {
        return sdf_yyyymmddhhmmss_short.format(date);
    }

    /**
     * 计算时间（几月后某天某一时刻）
     *
     * @param handleTime 需处理时间
     * @param month      几个月后
     * @param day        设置日期
     * @param hour       设置时间
     * @param minute     设置分钟
     * @param second     设置秒
     * @return
     * @author zhaofh 2016年9月27日
     */
    public static Date mathAfterMonth(Date handleTime, int month, int day, int hour, int minute, int second) {
        Calendar canlandar = Calendar.getInstance();
        canlandar.setTime(handleTime);
        canlandar.set(Calendar.MONTH, canlandar.get(Calendar.MONTH) + month);
        canlandar.set(Calendar.DATE, day);
        canlandar.set(Calendar.HOUR_OF_DAY, hour);
        canlandar.set(Calendar.MINUTE, minute);
        canlandar.set(Calendar.SECOND, second);
        Date date = canlandar.getTime();
        return date;
    }

    /**
     * 获取当月第一天
     *
     * @return
     * @author zhaofh 2016年10月21日
     */
    public static Date monthFirstDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取今天00:00:00
     *
     * @return
     * @author zhaofh 2016年10月21日
     */
    public static Date dayFirstDay() {
        return stringParseDateForYMD(dateParseStringForYMD(new Date()));
    }

    /**
     * 几小时几分钟前
     *
     * @param mathDate
     * @param hourInt
     * @param minInt
     * @return
     * @author zhaofh 2016年10月27日
     */
    public static Date beforeDate(Date mathDate, int hourInt, int minInt) {
        Calendar c = Calendar.getInstance();
        c.setTime(mathDate);
        c.add(Calendar.HOUR_OF_DAY, 0 - hourInt);
        c.add(Calendar.MINUTE, 0 - minInt);
        return c.getTime();
    }

    /**
     * 多少天后
     *
     * @param daySum
     * @return
     * @author zhaofh 2017年2月13日
     */
    public static Date laterDate(int daySum) {
        Date date = new Date();
        Calendar canlandar = Calendar.getInstance();
        canlandar.setTime(date);
        canlandar.set(Calendar.YEAR, canlandar.get(Calendar.YEAR));
        canlandar.set(Calendar.DATE, canlandar.get(Calendar.DATE) + daySum);
        return canlandar.getTime();
    }

    /**
     * 多少年多少月多少天后
     *
     * @param handleTime 需处理时间
     * @param year
     * @param month
     * @param day
     * @return
     * @author xsy 2017年5月11日
     */
    public static Date laterYMD(Date handleTime, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(handleTime);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        return calendar.getTime();
    }

    /**
     * 得到星期几
     *
     * @return
     * @author xsy 2017年10月18日
     */
    public static String getWeekToday() {
        String weekStr = "";
        Calendar calendar = Calendar.getInstance();
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                weekStr = "星期天";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
        }
        return weekStr;
    }

    /**
     * 获取短时间格式 返回字符串
     *
     * @return
     * @author tangjq 2017年11月3日
     */
    public static String getshortDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

    /**
     * 格式转化 Date2String  by yyyyMMddHHmm
     *
     * @param date
     * @return
     * @author xsy 2017年6月10日
     */
    public static String dateParseStringForYMDHM2(Date date) {
        return sdfyyyymmddhhmm.format(date);
    }

    /**
     * string2Date by yyyyMMddHHmmss
     *
     * @param date
     * @return
     * @author xsy 2017年12月13日
     */
    public static Date stringParseDateForYMDHMSS(String date) {
        try {
            return sdf_yyyymmddhhmmss_short.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某天的第一秒
     *
     * @param date
     * @return
     * @author tangjq 2018年2月5日
     */
    public static Date getFirstSecondOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至1
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 此时间是不是和现在在同一分钟
     *
     * @param theDateString
     * @return
     * @author xsy 2018年3月22日
     */
    public static boolean equalityMinutes(String theDateString) {
        Date theDate = stringParseDateForYMDHM(theDateString);
        Date nowDate = stringParseDateForYMDHM(dateParseStringForYMDHM(new Date()));
        if (nowDate.getTime() == theDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式转化 String2date by yyyyMMdd
     *
     * @param
     * @return Date
     * @author zhaofh 2016年8月16日
     */
    public static Date stringParseDateForYMDS(String date) {
        try {
            return sdf_yyyyMMdd_short.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式转化 Date2String  by yyyyMMdd
     *
     * @param
     * @return Date
     * @author zhaofh 2016年8月16日
     */
    public static String dateParseStringForYMDS(Date date) {
        return sdf_yyyyMMdd_short.format(date);
    }

    /**
     * 获取本月的最大天数
     *
     * @return
     * @author xsy 2018年5月14日
     */
    public static int getMonthMaxDay() {
        Calendar cal = Calendar.getInstance();
        return cal.getMaximum(Calendar.DATE);
    }

    /**
     * 获得指定日期的月份的最大天数
     *
     * @param theDate
     * @return
     * @author xsy 2018年6月12日
     */
    public static int getMonthMaxDayOfTheDate(Date theDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        cal.roll(Calendar.DATE, -1);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取指定日期的前一天的最后时刻
     *
     * @param theDay=指定日期
     * @return
     * @author xsy 2018年6月25日
     */
    public static Date lastDayEndTime(Date theDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDay);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 计算相差分钟
     *
     * @param afterDate
     * @param beforeDate
     * @return
     * @author xsy 2018年6月26日
     */
    public static int getMinuteSpace(Date afterDate, Date beforeDate) {
        long afterTime = afterDate.getTime();
        long beforeTime = beforeDate.getTime();
        if (afterTime < beforeTime) {
            return 0;
        } else {
            return (int) ((afterTime - beforeTime) / (1000 * 60));
        }
    }

    /**
     * 获取指定日期的星期几
     *
     * @param theDay
     * @return
     * @author xsy 2018年6月26日
     */
    public static String getWeekToTheDay(Date theDay) {
        String weekStr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(theDay);
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                weekStr = "星期天";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
        }
        return weekStr;
    }

    /**
     * 获取现在yyyy-MM-dd HH:mm:ss时间
     *
     * @return
     */
    public static Date getNowDateYMDHMS() {
        return stringParseDateForYMDHMS(dateParseStringForYMDHMS(new Date()));
    }

    /**
     * 获取今天yyyy-MM-dd时间
     *
     * @return
     */
    public static Date getNowDateYMD() {
        return stringParseDateForYMD(dateParseStringForYMD(new Date()));
    }

    /**
     * 获取指定日期的yyyy-MM-dd时间
     *
     * @return
     */
    public static Date getTheDateYMD(Date date) {
        return stringParseDateForYMD(dateParseStringForYMD(date));
    }

    /**
     * 获取某天的最后时刻
     *
     * @param theDay
     * @return
     */
    public static Date theDayEndTime(Date theDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 多少天后
     *
     * @param theDay 需处理时间
     * @param daySum 多少天后
     * @return
     */
    public static Date laterDate(Date theDay, int daySum) {
        Calendar canlandar = Calendar.getInstance();
        canlandar.setTime(theDay);
        canlandar.set(Calendar.YEAR, canlandar.get(Calendar.YEAR));
        canlandar.set(Calendar.DATE, canlandar.get(Calendar.DATE) + daySum);
        return canlandar.getTime();
    }

}
