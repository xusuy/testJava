package com.java8.defaultable;

/**
 * @author xsy
 * @create 2018-11-20 10:12
 * @desc
 **/
public class OverridableImpl implements Defaultable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }

    @Override
    public void t1() {

    }
}
