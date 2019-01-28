package com.domain;

/**
 * @author xsy
 * @create 2018-12-21 13:46
 * @desc
 **/
public class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
