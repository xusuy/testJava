package com.frame.controller;

import com.frame.entity.User;
import com.frame.model.UserModel;
import com.frame.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xsy
 * @create 2019-07-09 15:30
 * @desc
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private AccountService accountService;

    public TestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "getAccountById")
    public Object getAccountById(@RequestParam String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(value = "testTransation")
    public void testTransation() {
        accountService.testTransation();
    }

    @PostMapping(value = "addUser")
    public User addUser(UserModel userModel) {
        return accountService.addUser(userModel);
    }

}
