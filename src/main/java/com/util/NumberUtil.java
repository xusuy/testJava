package com.util;

import lombok.experimental.var;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author xsy
 * @create 2018-12-06 15:52
 * @desc
 **/
public class NumberUtil {

    @Test
    public void testNumber() {
        Double aDouble = Double.valueOf(1) / Double.valueOf(2);
        Double bDouble = Double.valueOf(1 / 2);
        System.out.println(aDouble);
        System.out.println(bDouble);
        System.out.println(1 / 2);
        System.out.println(1.0 / 2.0);
        double c = 0.0;
        System.out.println(c == 0);

        boolean naN = Float.isNaN(0.000000000001f);
        System.out.println("Float.isNaN：" + naN);

        System.out.println(Integer.MAX_VALUE == (1 << 31) - 1);
    }

    @Test
    public void testOther() {
        //null强转
        Object o1 = null;
        String o2 = (String) o1;
        System.out.println(o2);

        //
        StringBuffer sb = new StringBuffer();
        sb.append("a").append("\\$");
        System.out.println(sb.toString());
        String str1 = "1$2";
        List list1 = Arrays.asList(str1.split("\\$"));
        list1.forEach(o -> System.out.println(o));
    }

    @Test
    public void test1() {
        int a = 0;
        int b = a++;
        int c = ++a;
        System.out.println(b + "");//0
        System.out.println(c + "");//2

//        int d = 5;
////        while (true) {
////            if (d-- > 0) {//先返回值比较>0后运算
////                System.out.print(d);//43210
////            }else return;
////        }
        int f = 5;
        while (true) {
            if (--f > 0) {//先运算比较>0后返回值
                System.out.print(f);//4321
            } else return;
        }
    }

    @Test
    public void test2() {
        Integer num = null;
        //NullPointerException
        int nun = num;
    }

    int x() {
        char a = 'a';
        return (int) a;
    }

    @Test
    public void test3() {
        double d = 10.42;
        System.out.println(Math.floor(d));//floor(地板)：小于或等于参数且等于数学整数的浮点值
        System.out.println(Math.ceil(d));//ceil(天花板)：大于或等于参数且等于数学整数的浮点值
//        var i = "10.42";
//        System.out.println(Integer.parseInt(i));
    }

    @Test
    public void theTypeTest() {
        //是否正整型数字
        System.out.println(NumberUtils.isDigits("12"));
        //是否数字包括小数
        System.out.println(NumberUtils.isNumber("12.0001"));
    }
}
