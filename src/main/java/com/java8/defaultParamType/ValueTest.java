package com.java8.defaultParamType;

/**
 * @author xsy
 * @create 2018-11-20 10:30
 * @desc：参数Value.defaultValue()的类型由编译器推导得出，不需要显式指明。 在Java 7中这段代码会有编译错误，除非使用Value.<String>defaultValue()
 **/
public class ValueTest {
    public static void main(String[] args) {
        final Value<String> valueStr = new Value<>();
        final Value<Integer> valueInteger = new Value<>();
        final Value<Double> doubleValueInteger = new Value<>();
        String orDefault = valueStr.getOrDefault("1", Value.defaultValue());
        Integer orDefault1 = valueInteger.getOrDefault(2, Value.defaultValue());
        Double orDefault2 = doubleValueInteger.getOrDefault(3.3, Value.defaultValue());

        final Value<Integer> valueInteger1 = new Value<>();
        Integer orDefault3 = valueInteger1.getOrDefault(123, 0);

        final Value<Integer> valueInteger2 = new Value<>(Integer.class);
        System.out.println(valueInteger2.theClass().getSimpleName());
    }
}
