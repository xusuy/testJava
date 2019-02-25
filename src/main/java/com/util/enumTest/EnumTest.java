package com.util.enumTest;

public enum EnumTest {
    ONE(1, "v1"),
    TWO(2, "val2"),
    NULL(3, "");

    int id;
    String value;

    EnumTest(int id, String value) {
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

    public static EnumTest matchOpCode(String opCodeStr) {
        for (EnumTest enumTest : EnumTest.values()) {
            if (enumTest.name().equalsIgnoreCase(opCodeStr)) {
                return enumTest;
            }
        }
        return EnumTest.NULL;
    }

    public static EnumTest getEnumTestByOpCodeStr(String opCodeStr) {
        EnumTest enumTest = NULL;
        switch (matchOpCode(opCodeStr)) {
            case ONE:
                enumTest = ONE;
                break;
            case TWO:
                enumTest = TWO;
                break;
        }
        return enumTest;
    }

    public static void main(String[] args) {
        EnumTest enumTest = getEnumTestByOpCodeStr("one");
        System.out.println(enumTest.name());
    }
}
