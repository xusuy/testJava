package com.frame.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author xsy
 * @create 2019-08-29 11:29
 * @desc
 **/
@Getter
@Setter
@Accessors(chain = true)
public class User2 {
    /**
     *  id
     */
    private String id;


    /**
     *  名称
     */
    private String name;

    /**
     *  手机号
     */
    private String phoneNo;

    /**
     *  userName
     */
    private String userName;

}
