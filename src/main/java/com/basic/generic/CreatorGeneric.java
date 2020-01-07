package com.basic.generic;

public class CreatorGeneric {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.f();
    }
}

abstract class GenericWithCreate<T> {
    final T element;

    GenericWithCreate() {//在构造方法中初始化element
        element = create();//多态
    }

    protected abstract T create();
}

class X {

}

class Creator extends GenericWithCreate<X> {

    @Override
    protected X create() {
        return new X();
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

