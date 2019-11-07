package com.util;

import com.domain.User;

/**
 * @author xsy
 * @create 2017-02-19 11:12
 * @desc system.arrayCopy深浅复制
 **/
//System中提供了一个native静态方法arraycopy(),可以使用这个方法来实现数组之间的复制。
// 对于一维数组来说，这种复制属性值传递，修改副本不会影响原来的值。对于二维或者一维数组中存放的是对象时，
// 复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
public class SytemArrayCopy {
    public static void main(String[] args) {
        //初始化原对象数组
        User[] users = new User[]{
                new User(1, "admin", "admin@qq.com"),
                new User(2, "maco", "maco@qq,com"),
                new User(3, "kitty", "kitty@qq,com")
        };
        User[] target = new User[users.length];//新建一个目标对象数组(副本)
        System.arraycopy(users, 0, target, 0, users.length);//实现复制
        System.out.println("是否同一数组" + (users == target));
        System.out.println("源对象与目标对象的物理地址是否一样：" + (users[0] == target[0] ? "浅复制" : "深复制"));
        target[0].setEmail("admin@sina.com");
        System.out.println("修改目标对象的属性值后源对象users：");
        for (User user : users) {
            System.out.println(user);
        }
    }

}
