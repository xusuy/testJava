package com.frame.service.impl;

import com.frame.entity.User;
import com.frame.entity.User2;
import com.frame.mapper.AccountMapper;
import com.frame.model.UserModel;
import com.frame.service.AccountService;
import com.util.UUIDGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
    private ModelMapper mapper;

    public AccountServiceImpl(AccountMapper accountMapper, ModelMapper mapper) {
        this.accountMapper = accountMapper;
        this.mapper = mapper;
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

    @Override
    public Object getAccountById2(String id) {
        User2 user2_1 = accountMapper.getAccountById2(id);
        User2 user2_2 = accountMapper.getAccountByPhoneNo2("11111111111");
        List<User2> users = new ArrayList<User2>() {
            {
                add(user2_1);
                add(user2_2);
            }
        };
        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransation() {
        boolean bool = accountMapper.updateUserNameById("15EC8080-5E40-4F9D-BDC0-05D10E495D73");
        int i = 0;
        int r = 10 / i;
    }

    @Override
    public User addUser(UserModel userModel) {
        //方法1：使用spring BeanUtils
//        User user = new User();
//        BeanUtils.copyProperties(userModel, user);
        //方法2：使用org ModelMapper：可以动态设置源类和目标类的字段映射关系
        User user = mapper.map(userModel, User.class)
                .setId(UUIDGenerator.sequentialUUIDString())
                .setTenantId(UUIDGenerator.sequentialUUIDString());//链式代码
        accountMapper.addUser(user);
        return user;
    }
}
