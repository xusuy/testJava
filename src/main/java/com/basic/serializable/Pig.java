package com.basic.serializable;

import java.io.Serializable;

/**
 * @author xsy
 * @create 2019-02-28 10:49
 * @desc
 **/
public class Pig implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String name;

    /**
     * value
     */
    private String vale;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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
        return "Pig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vale='" + vale + '\'' +
                '}';
    }
}
