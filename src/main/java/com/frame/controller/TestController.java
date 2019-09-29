package com.frame.controller;

import com.frame.entity.User;
import com.frame.model.UserModel;
import com.frame.service.AccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xsy
 * @create 2019-07-09 15:30
 * @desc
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private AccountService accountService;
    @Resource
    private RestTemplate restTemplate;

    public TestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "getAccountById")
    public User getAccountById(@RequestParam String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(value = "getAccountByIdV/{id}")
    public User getAccountByIdV(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(value = "getAccountById2")
    public Object getAccountById2(@RequestParam String id) {
        return accountService.getAccountById2(id);
    }

    @GetMapping(value = "testTransation")
    public void testTransation() {
        accountService.testTransation();
    }

    @PostMapping(value = "addUser")
    public User addUser(UserModel userModel) {
        return accountService.addUser(userModel);
    }

    @PostMapping(value = "updateUserMap")
    public User updateUserMap(@RequestBody Map<String,String> mapData){
        return accountService.getAccountById(mapData.get("id"));
    }

    @PostMapping(value = "updateUser")
    public User updateUser(@RequestBody UserModel userModel) {
        return accountService.updateUser(userModel);
    }

    @GetMapping(value = "queryUserInfo")
    public User queryUserInfo(UserModel userModel, HttpServletRequest request) {
        String userId = request.getHeader("Accept");
        String header1 = request.getHeader("header1");
        return accountService.queryUserInfo(userModel);
    }
}
