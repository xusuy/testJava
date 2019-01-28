package com.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author xsy
 * @create 2018-11-20 10:45
 * @desc 获取参数名称
 **/
public class ParameterNames {
    public static void main(String[] args) throws Exception {
        Method method = ParameterNames.class.getMethod("main", String[].class);
        for (Parameter parameter : method.getParameters()) {
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
