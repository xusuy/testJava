package com.basic.generic;

/**
 * @author xsy
 * @create 2019-03-01 13:49
 * @desc
 **/
public class User {
    private final int id;

    private static int age = 123;

    private final String name = "jack";

    private final static int phone = 181;

    private final int size = 20;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public int getSize() {
        return size;
    }
}
