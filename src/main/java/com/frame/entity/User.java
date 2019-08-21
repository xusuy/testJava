package com.frame.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author xsy
 * @create 2019-07-09 16:38
 * @desc
 **/
@Getter
@Setter
@Accessors(chain = true)//支持链式操作
@RequiredArgsConstructor(staticName = "of")//创建设置name对象的静态构造方法User.of('xiaoming')
public class User {
    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    @NonNull
    private String name;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * mailbox
     */
    private String mailbox;

    /**
     * 登录名
     */
    private String userName;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * tid
     */
    private String tid;
}
