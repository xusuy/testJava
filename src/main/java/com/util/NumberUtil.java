package com.util;

import lombok.experimental.var;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public void testjjjj() {
        int a = 0;
        int b = a++;
        System.out.println(b);//0
        System.out.println(a);//1
        int c = ++a;
        System.out.println(c);//2

        System.out.print("j--：");
        for (int j = 5; j > 0; j--) {
            System.out.print(j);//54321
        }
        System.out.println();
        System.out.print("--j：");
        for (int j = 5; j > 0; --j) {
            System.out.print(j);//54321
        }
        System.out.println();
        int d = 5;
        while (true) {
            if (d-- > 0) {//先比较>0后运算
                System.out.print(d);//43210
            } else return;
        }

//        int f = 5;
//        while (true) {
//            if (--f > 0) {//先运算后比较>0
//                System.out.print(f);//4321
//            } else return;
//        }
    }

    @Test
    public void test2() {
        Integer num = null;
        //NullPointerException
        int nun = num;
    }

    @Test
    void x() {
        char a = 'a';
        System.out.println((int) a);
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
        System.out.println(NumberUtils.isNumber("-12.0001"));
        System.out.println(NumberUtils.isNumber("null"));
    }

    @Test
    public void test4() {
        Integer intg = 100;
        System.out.println(intg.equals(100));
        System.out.println(intg == 100l);//100L
        Integer t1 = 128;
        Integer t2 = 128;
        System.out.println(t1 == t2);//IntegerCache[-128,127]
    }

    @Test
    public void test5() {
        double a = 2;
        double b = a / 3;
        System.out.println(b);
        double c = a + 3 / 2;
        System.out.println(c);
        float f1 = 0.75f;
        int int1 = (int) (f1 * 16);
        System.out.println(int1);
    }

    @Test
    public void typeCast() {
        Object o1 = "12.9";
        //Double d1 = (Double) o1;//java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Double
        String s1 = "+12.9";
        System.out.println(Double.valueOf(s1));
        String dataValue = (null + "").replace("+", "");
        Double d2 = NumberUtils.isNumber(dataValue) ? Double.valueOf(dataValue) : null;
        System.out.println(d2);
    }

    @Test
    public void whileTest() {
        //会先做一次do
        do {
            System.out.println(123);
        } while (1 > 2);
    }

    @Test
    public void testZero() {
        //一个double数/0或者/0.0是一个无穷数
        System.out.println(1.0 / 0);
        System.out.println(1.0 / 0.0);
        System.out.println(1.0 / 1);
        double agreedPoll = 0.0;
        double countersignRatio = 1.0;
        int countPoll = 0;
        System.out.println(((agreedPoll + 1) / countPoll) >= countersignRatio);
    }

    @Test
    public void testFloat() {
        Float f1 = 1.1f;
        f1.equals(1.2);
    }
}
