package com.basic.serializable;

import java.io.Serializable;

/**
 * @author xsy
 * @create 2017-02-28 10:31
 * @desc
 **/
public class Dog extends Animal implements Serializable {
    private static int staticId = 10;
    /**
     * id
     */
    private transient int id;//不被序列化

    /**
     * name
     */
    private String name;

    /**
     * value
     */
    private String vale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVale() {
        return vale;
    }

    public void setVale(String vale) {
        this.vale = vale;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vale='" + vale + '\'' +
                ", code='" + code + '\'' +
                ", staticId='" + staticId + '\'' +
                '}';
    }
}
