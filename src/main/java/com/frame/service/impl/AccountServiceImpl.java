package com.frame.service.impl;

import com.frame.entity.User;
import com.frame.mapper.AccountMapper;
import com.frame.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xsy
 * @create 2019-07-09 15:30
 * @desc
 **/
@Service
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Object getAccountById(String id) {
        User user1 = accountMapper.getAccountById(id);
        User user2 = accountMapper.getAccountByPhoneNo("11111111111");
        List<User> users = new ArrayList<User>() {
            {
                add(user1);
                add(user2);
            }
        };
        return users;
    }
}
