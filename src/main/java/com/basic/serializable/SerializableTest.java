package com.basic.serializable;

import org.codehaus.jackson.map.ObjectWriter;

import java.io.*;

/**
 * @author xsy
 * @create 2017-02-28 10:33
 * @desc
 **/
public class SerializableTest {

    public static void serializa(Object object) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\temp\\serializa\\obj.txt")));
        oos.writeObject(object);
        oos.close();
        System.out.println("序列化成功");
        System.out.println(object);
    }

    public static Dog desSerializaDog() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\temp\\serializa\\obj.txt")));
        Dog dog = (Dog) ois.readObject();
        System.out.println("dog反序列化成功");
        System.out.println(dog);
        return dog;
    }

    public static Pig desSerializaPig() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\temp\\serializa\\obj.txt")));
        Pig pig = (Pig) ois.readObject();
        System.out.println("pig反序列化成功");
        System.out.println(pig);
        return pig;
    }

    public static void main(String[] args) throws Exception {
        Dog dog = (Dog) InstanceFactory.createInstance("com.basic.serializable.Dog");
        dog.setVale("dog1");
        dog.setName("小黑");
        dog.setId(3);
        dog.setCode(9);
        serializa(dog);
        Dog desDog = desSerializaDog();
        //反序列化后的对象和序列化前的对象不是同一个，只是属性是用到了之前的属性，
        // 如果是单例模式（需要只有一个实例），可以在单例类中实现readResolve()方法直接返回当前实例即可
        System.out.println(dog.equals(desDog));
//        Pig pig = (Pig) InstanceFactory.createInstance("com.basic.serializable.Pig");
//        pig.setVale("pig1");
//        pig.setName("gg1");
//        pig.setId(5);
//        serializa(pig);
//        desSerializaPig(pig);
    }


}
