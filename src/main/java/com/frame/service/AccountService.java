package com.frame.service;

import com.frame.entity.User;
import com.frame.model.UserModel;

import java.util.List;

/**
 * @author xsy
 * @create 2019-07-09 15:29
 * @desc
 **/
public interface AccountService {
    User getAccountById(String id);

    void testTransation();

    User addUser(UserModel userModel);

    Object getAccountById2(String id);

    User updateUser(UserModel userModel);

    User queryUserInfo(UserModel userModel);

    List<User> getList(String id);
}
