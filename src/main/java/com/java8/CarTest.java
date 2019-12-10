package com.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author xsy
 * @create 2018-11-19 17:40
 * @desc 四种类型的方法引用
 **/
public class CarTest {
    //第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
    final Car car = Car.create(Car::new);
    //lambda形式：
//    final Car car = Car.create(() -> new Car());
    final List<Car> cars = Arrays.asList(car);

    @Test
    public void test() {
        //第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数
        cars.forEach(car2 -> Car.collide(car2));
        //第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
        cars.forEach(car1 -> car1.repair());
        //匿名内部类形式
        cars.forEach(new Consumer<Car>() {
            @Override
            public void accept(Car car1) {
                car1.repair();
            }
        });
        //第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
        final Car police = Car.create(Car::new);
        /*final Car police = Car.create(new Supplier<Car>() {
            @Override
            public Car get() {
                return new Car();
            }
        });*/
        cars.forEach(another -> police.follow(another));
        //匿名内部类形式
        cars.forEach(new Consumer<Car>() {
            @Override
            public void accept(Car another) {
                police.follow(another);
            }
        });
    }
}
