package com.frame.service;

import com.frame.entity.User;
import com.frame.model.UserModel;

/**
 * @author xsy
 * @create 2019-07-09 15:29
 * @desc
 **/
public interface AccountService {
    Object getAccountById(String id);

    void testTransation();

    User addUser(UserModel userModel);
}
