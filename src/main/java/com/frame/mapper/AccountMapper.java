package com.frame.mapper;

import com.frame.entity.Account;
import com.frame.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author xsy
 * @create 2019-07-09 14:17
 * @desc
 **/
public interface AccountMapper {

    @Select("select * from `archived_15ec21a1-caf1-42c3-be18-4958b2b2d5e8` where id=#{id}")
    @Results(id = "userResult", value = {
        @Result(column = "id", property = "id", id = true),
        @Result(column = "c_cf5d7e30dd5d44d8b2850f163b4d2f28", property = "name"),
        @Result(column = "c_40d527e879cd451fad1cb7229f06927d", property = "phoneNo")
    })
    User getAccountById(String id);

    @Select("select * from `archived_15ec21a1-caf1-42c3-be18-4958b2b2d5e8` where `c_40d527e879cd451fad1cb7229f06927d`=#{phoneNo}")
    @ResultMap(value = "userResult")
    User getAccountByPhoneNo(String phoneNo);

    boolean updateUserNameById(String id);
}
