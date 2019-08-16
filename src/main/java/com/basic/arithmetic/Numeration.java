package com.basic.arithmetic;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author xsy
 * @create 2019-08-06 16:52
 * @desc 计算相关
 **/
public class Numeration {

    @Test
    public void test1() {
        String range = "-10";
        BigDecimal bg = new BigDecimal(range.split("\\-")[0]);
        System.out.println(bg.intValue());
    }
}
