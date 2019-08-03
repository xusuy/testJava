package com.frame.entity;

/**
 * @author xsy
 * @create 2019-07-09 16:38
 * @desc
 **/
public class User {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
