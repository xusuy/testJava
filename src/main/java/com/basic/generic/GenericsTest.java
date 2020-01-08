package com.basic.generic;

import com.java8.Car;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xsy
 * @create 2017-02-26 10:04
 * @desc 泛形
 * https://blog.csdn.net/briblue/article/details/76736356
 **/
public class GenericsTest {

    public void test1() {
        Test<String> testString = new Test<>();
        Integer integer = testString.method2(1);//类的T和方法的T无关
    }

    /**
     * ?是无限定通配符：Collection<?> collection无需关注具体类型，只能调用collection与类型无关的方法
     * <?>被称作无限定的通配符。
     * <? extends T>被称作有上限的通配符。
     * <? super T>被称作有下限的通配符。
     *
     * @param collection
     */
    public void testWildCards(Collection<?> collection) {
        //编译无法通过
//        collection.add(1);
//        collection.add("2");
        int size = collection.size();

        List<?> wildlist = new ArrayList<String>();
//        wildlist.add("123");// 编译不通过
        int size1 = wildlist.size();
        List<? extends User> userList1 = new ArrayList<UserImpl>();
//        userList1.add(new UserImpl(1));// 编译不通过

        List<? super User> userList2 = new ArrayList<>();
        userList2.add(new UserImpl(1));

        List<Object> objectList = new ArrayList<>();
        objectList.add("3");
    }

    public static <E> E test2(E e) {
        Collection<E> list = new ArrayList<>();
        list.add(e);
        System.out.println(list);
        return e;
    }

    public static Object test3(Object o) {
        List<Object> list = new ArrayList<>();
        list.add(o);
        System.out.println(list);
        return o;
    }

    @org.junit.Test
    public void test4() throws NoSuchFieldException, IllegalAccessException {
        //只要类没有在编译期初始化值，即使使用final修饰的字段，都能通过运行期放射改变
        finalReflectTest1();
        System.out.println("====");
        finalReflectTest2();
        System.out.println("====");
        finalReflectTest3();
        System.out.println("====");
        finalReflectTest4();
        System.out.println("====");
        finalReflectTest5();
        System.out.println("====");
        //不能给final static同时修饰的字段 放射赋值：IllegalAccessException
//        finalReflectTest6();
    }

    private void finalReflectTest6() throws NoSuchFieldException, IllegalAccessException {
        User user = new User(5);
        System.out.println("反射之前的phone=" + user.getPhone());
        Field user_field = User.class.getDeclaredField("phone");
        user_field.setAccessible(true);
        user_field.setInt(user, 182);
        System.out.println("反射后的field,phone=" + user_field.get(user));
        System.out.println("反射后user,phone=" + user.getPhone());
    }

    private void finalReflectTest5() throws NoSuchFieldException, IllegalAccessException {
        User user = new User(5);
        System.out.println("反射之前的size=" + user.getSize());
        Field user_field = User.class.getDeclaredField("size");
        user_field.setAccessible(true);
        user_field.setInt(user, 30);
        System.out.println("反射后的field,size=" + user_field.get(user));
        System.out.println("反射后user,size=" + user.getSize());
    }

    public void finalReflectTest1() throws NoSuchFieldException, IllegalAccessException {
        Integer id = new Integer(1);
        Field int_field = id.getClass().getDeclaredField("value");
        int_field.setAccessible(true);
        int_field.setInt(id, 3);
        System.out.println(id);
    }

    public void finalReflectTest2() throws NoSuchFieldException, IllegalAccessException {
        User user = new User(5);
        System.out.println("反射之前的id=" + user.getId());
        Field user_field = User.class.getDeclaredField("id");
        user_field.setAccessible(true);
        user_field.setInt(user, 6);
        System.out.println("反射后的field,id=" + user_field.get(user));
        System.out.println("反射后user,id=" + user.getId());
    }

    public void finalReflectTest3() throws NoSuchFieldException, IllegalAccessException {
        User user = new User(5);
        System.out.println("反射之前的age=" + user.getAge());
        Field user_field = User.class.getDeclaredField("age");
        user_field.setAccessible(true);
        user_field.setInt(user, 6);
        System.out.println("反射后的field,age=" + user_field.get(user));
        System.out.println("反射后user,age=" + user.getAge());
    }

    public void finalReflectTest4() throws NoSuchFieldException, IllegalAccessException {
        User user = new User(5);
        System.out.println("反射之前的name=" + user.getName());
        Field user_field = User.class.getDeclaredField("name");
        user_field.setAccessible(true);
        user_field.set(user, "rose");
        System.out.println("反射后的field,name=" + user_field.get(user));
        System.out.println("反射后user,name=" + user.getName());
    }

    public static void test5() {
        List<Integer> li1 = new ArrayList<>();
        //不能创建具体类型的泛型数组,由于类型擦除
//        List<Integer>[] li2 = new ArrayList<Integer>[8];
//        List<Boolean>[] li3 = new ArrayList<Boolean>[8];

        //可以通过通配符?创建，但是只能读不能写
        List<?>[] li4 = new ArrayList<?>[8];
        li4[0] = new ArrayList<>();
        List<?> v = li4[0];
        v.get(0);
//        v.set(3);
    }

    /**
     * 接收一个泛型数组，然后创建一个长度与接收的数组长度一样的泛型数组，
     * 并把接收的数组的元素复制到新创建的数组中，
     * 最后找出新数组中的最小元素，并打印出来
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<T>> void getMinFromArray(T[] a) {
        //通过反射创建相同类型的数组
        T[] b = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        T min = null;
        boolean flag = true;
        for (int i = 0; i < b.length; i++) {
            if (flag) {
                min = b[i];
                flag = false;
            }
            if (b[i].compareTo(min) < 0) {
                min = b[i];
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) {
        //编译通过！
        Class<? extends Number> clazz = Integer.class;
        //赋予其他类型
        clazz = double.class;
        clazz = Number.class;

        Integer inaArray[] = {1, 4, 7, 2, 8};
        getMinFromArray(inaArray);
        String strArray[] = {"3", "5", "1", "0"};
        getMinFromArray(strArray);

        Car car = new Car();
        Object object = new Object();
//        object = car;
//        car = (Car) object;//ClassCastException
        if (object instanceof Car) {
            car = (Car) object;
        }

        //List<E>泛形不需要强转
        String test2 = test2("test2");
        Integer integer_2 = test2(2);

        //需要强转
        String test3 = (String) test3("test3");
        Integer integer_3 = (Integer) test3(3);
    }
}
