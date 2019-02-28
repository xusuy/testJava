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
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\temp\\serializa\\dog.txt")));
        oos.writeObject(object);
        oos.close();
        System.out.println("序列化成功");
    }

    public static void desSerializaDog(Object object) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\temp\\serializa\\dog.txt")));
        if (object instanceof Dog) {
            Dog dog = (Dog) ois.readObject();
            System.out.println("dog反序列化成功");
            System.out.println(dog);
        } else if (object instanceof Pig) {
            Pig pig = (Pig) ois.readObject();
            System.out.println("Pig反序列化成功");
            System.out.println(pig);
        }
    }

    public static void main(String[] args) throws Exception {
        Dog dog = (Dog) InstanceFactory.createInstance("com.basic.serializable.Dog");
        dog.setVale("dog1");
        dog.setName("ww2");
        dog.setId(3);
        dog.setCode(9);
        serializa(dog);
        desSerializaDog(dog);

//        Pig pig = (Pig) InstanceFactory.createInstance("com.basic.serializable.Pig");
//        pig.setVale("pig1");
//        pig.setName("gg1");
//        pig.setId(5);
//        serializa(pig);
//        desSerializaDog(pig);
    }


}
