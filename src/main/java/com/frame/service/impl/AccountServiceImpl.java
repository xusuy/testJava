package com.frame.service.impl;

import com.frame.entity.Account;
import com.frame.entity.User;
import com.frame.mapper.AccountMapper;
import com.frame.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransation() {
        boolean bool = accountMapper.updateUserNameById("15EC8080-5E40-4F9D-BDC0-05D10E495D73");
        int i = 0;
        int r = 10/i;
    }
}
