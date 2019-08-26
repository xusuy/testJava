package com.basic;

import com.frame.entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author xsy
 * @create 2017-02-21 15:09
 * @desc string
 **/
public class StringTest {

    public static void main(String[] args) {
        //subString：jdk6和jdk7的区别
        String str1 = "abcdef";
        str1 = str1.substring(3, 3);
        System.out.println(str1);

        //replace：
        //  字符串中\需要用来“\\”表示，例如System.out.println( "\\" ) ;只打印出一个"\"。" +
        //  正则表达式中也用\表示转义。\在正则表示式中也作为转义字符使用，所以在正则表达式中"\\\\"才表示一个反斜杠，即"\"
        //  对于replaceFirst和replaceAll中的replacement，如果其中包含\的话，将作为转义字符看待
        System.out.println("aaa".replace("a", "\\."));
        System.out.println("aaa".replace("a", "\\\\."));
        System.out.println("aaa".replaceAll("a", "\\."));
        System.out.println("aaa".replaceAll("a", "\\\\."));
        System.out.println("aaa".replaceFirst("a", "\\."));
        System.out.println("aaa".replaceFirst("a", "\\\\."));
//        String testUrl = "https://qxymgr-d.ywsoftware.cn/files\\upload\\201901\\ba4e7775-52c4-4aba-864f-804c306b2279.png";
        String testUrl = "https://qxymgr-d.ywsoftware.cn/files/upload/201901/ba4e7775-52c4-4aba-864f-804c306b2279.png";
        System.out.println("replace testUrl=" + testUrl.replace("\\", "/"));
        int i = 1;
        String.valueOf(i);//内部通过调用Integer.toString(i)
        str1 = "abcdef";
        char char_1 = str1.charAt(0);
        String str_char = String.valueOf(char_1);
        System.out.println("str_char=" + str_char);

        // 通过对实参的hashCode()方法返回的int hash值与各个case中hashCode()方法返回的int hash值比较，所以实参和case不能为null
//        stringSwith(null);

        String s1 = "a";
        System.out.println((s1 + "b") == "ab");//运行时确定s1，s1 + "b"是堆中新对象
        String s4 = s1 + "d";
        String s5 = s1 + "d";
        System.out.println(s4 == s5);////运行时确定s4、s5，是堆中新对象
        //intern():如果字符串常量池中没有这个字符则添加，然后返回这个引用；如果有则直接返回这个引用
        System.out.println("intern() 比较======");
        System.out.println((s1 + "b").intern() == "ab");
        System.out.println(s4.intern() == s5.intern());

        String s2 = "c" + "d";//编译期常量折叠成String s2 = "cd"
        System.out.println(s2 == "cd");

    }

    public static String stringSwith(String id) {
        switch (id) {
            case "1":
                return "11";
            case "2":
                return "22";
        }
        return null;
    }

    @Test
    public void testEqualsOrDengyu() {
        Object o = "123";
        String s = "123";
        Object o2 = s;
        String s2 = (String) o;
        System.out.println(s.equals(o));

        String s3 = new String("123");
        System.out.println(s3 == s);
        System.out.println(s3.intern() == s);

    }

    @Test
    public void test2() {
//        String va = null;
//        System.out.println(va.replace(null,""));

        System.out.println(StringUtils.isNumeric("1.9"));//false
        System.out.println(isNumber("1.9"));
    }

    public boolean isNumber(String input) {
        if (input == null || "".equals(input)) {
            return false;
        }
        return Pattern.matches("-?[0-9]*(\\.?)[0-9]*", input);
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-04-08");
        System.out.println(date);
    }

    @Test
    public void test4() {
        int[] array = new int[2];//int基本类型数组被初始化为0
        System.out.println(array[0]);
    }

    @Test
    public void test5() {
        String s1 = "血常规：2019-06-27-1";
        String s2 = "血常规：2019-06-27-2";
        String s3 = s1.replace(s1, s2);
        System.out.println(s3);
    }

    //null + ""
    @Test
    public void test6() {
        String nullStr = null;
        String newStr = nullStr + "";
        System.out.println(newStr.length());
        if (StringUtils.isNotEmpty(newStr)) {
            System.out.println(newStr);
        }
    }

    //java.lang.String cannot be cast to java.lang.Integer
    @Test
    public void test7() {
        Object o = "6";
        int resectionNum = (Integer) o;
        System.out.println(o);
    }

    @Test
    public void test8() {
        String s = "a";
        char c = 'c';
        int i = 97;
        char[] ch = {'a'};
        System.out.println(s.equals(c));
        System.out.println(s.equals(i));
        System.out.println(s.equals(ch));
    }

    @Test
    public void theTypeTest() {
        System.out.println(NumberUtils.isDigits("NaN"));
    }

    public void fineTest() {
        //final修饰的普通变量，值不能改变
        final int i = 0;
//        i++;
        //final修饰的实例变量，引用不能改变，但引用的值可以改变
        final String str = "s";
//        str = "t";
        final Account account = new Account();
        account.setName("name");
//        account = new Account();
    }
}
