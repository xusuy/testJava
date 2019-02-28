package com.basic.serializable;

/**
 * @author xsy
 * @create 2019-02-28 10:52
 * @desc 实例创建工程——简单工程模式
 **/
public class InstanceFactory {

    public static Object createInstance(String className) throws Exception {
        Class<?> theClass = Class.forName(className);
        return theClass.newInstance();
    }
}
