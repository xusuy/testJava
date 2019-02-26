package com.util.enumTest;

public enum EnumTest2 {

    ONE(1, "v1"),
    TWO(2, "val2"),
    NULL(3, "");

    int id;
    String value;

    EnumTest2(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
