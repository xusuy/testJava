package com.java8;

import com.frame.entity.User;
import com.frame.entity.User2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author xsy
 * @desc java8 Optional
 **/
public class OptionalTest {

    @Test
    public void test() throws Throwable {
        String str1 = null;
        String str2 = "123";
        //获取字符串长度
        System.out.println(Optional.ofNullable(str2).map(String::length).orElse(0));
        //
        List<User2> userList = new ArrayList<User2>() {{
            add(new User2().setName("jack").setId("1"));
            add(new User2().setName("roce").setId("2"));
        }};
        //不满足条件时返回异常
        //User2 user = userList.stream().filter(u -> Objects.equals("jack1", u.getName())).findAny().orElseThrow(() -> new RuntimeException());
        Optional<User2> userv = userList.stream().filter(u -> Objects.equals("jack", u.getName())).findAny();
        //当满足条件时执行传入的参数化操作
        userv.ifPresent(u -> {
            System.out.println(u.getId() + "：" + u.getName());
        });
        if (userv.isPresent()) {
            User2 u = userv.get();
            System.out.println(u.getId() + "：" + u.getName());
        }
        //如果条件不成立时需要执行相对复杂的逻辑而不是简单的返回操作，则可以使用 orElseGet 实现
        User2 user2_xiaomei = userList.stream().filter(u -> Objects.equals("xiaomei", u.getName())).findAny().orElseGet(() ->
                new User2().setId("6").setName("xiaomei")
        );
        System.out.println(user2_xiaomei.getId() + "：" + user2_xiaomei.getName());
    }
}
