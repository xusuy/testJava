package com.spring;

import com.frame.entity.Account;
import com.frame.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

////需要开启localhost:8080服务
@ContextConfiguration("classpath:applicationContext-spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RestControllerTest {

    private static MultiValueMap<String, String> headers;
    static {
            //因为HttpEntity(T body, MultiValueMap<String, String> headers)中重新创建了HttpHeaders
            //所以使用MultiValueMap
            headers = new LinkedMultiValueMap<String, String>(){
                 {
                    add("header1","666");
                    add("Accept","application/json");
                 }
             };
    }

    @Resource
    private RestTemplate restTemplate;

    //get请求只能是?key1=value1&key2=value2的格式
    @Test
    public void testRestGet1() {
        //PathVariable
        String id = "1";
        ResponseEntity<Account> accountResponseEntity = restTemplate.getForEntity("http://localhost:8080/test/getAccountByIdV/{id}", Account.class, id);
        int statusCodeValue = accountResponseEntity.getStatusCodeValue();
        Assert.assertEquals(200,statusCodeValue);
        HttpHeaders responseHeaders = accountResponseEntity.getHeaders();
        Assert.assertNotNull("accountResponseEntity is null!",accountResponseEntity);
    }

    @Test
    public void testRestGet2() {
        //PathVariable
        String id = "2";
        ResponseEntity<Account> accountResponseEntity = restTemplate.getForEntity("http://localhost:8080/test/getAccountById?id={id}", Account.class, id);
        Assert.assertNotNull("accountResponseEntity is null!",accountResponseEntity);
    }

    @Test
    public void testRestGet3() {
        HttpEntity<UserModel> entity = new HttpEntity<>(headers);
        Map<String,Object> urlVariables = new HashMap<String,Object>(){
            {
                put("id","123123123123");
                put("phoneNumber","18467890213");
                put("userName","jack");
            }
        };
//        ResponseEntity<Account> accountResponseEntity = restTemplate.exchange("http://localhost:8080/test/queryUserInfo?id={id}&phoneNumber={phoneNumber}&userName={userName},", HttpMethod.GET, entity, Account.class, urlVariables);

        //使用UriComponentsBuilder
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/test/queryUserInfo");
        urlVariables.entrySet().forEach(v->urlBuilder.queryParam(v.getKey(),v.getValue()));
        String url = urlBuilder.build().encode().toString();
        System.out.println(url);
        ResponseEntity<Account> accountResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Account.class);

        Assert.assertNotNull("accountResponseEntity is null!",accountResponseEntity);
    }


    @Test
    public void testRestPost1() {
        UserModel userModel = new UserModel();
        userModel.setId("16AAF3B5-3170-4252-854C-8AC9A4E96D3E");
        userModel.setUserName("testName666");
        Account account = restTemplate.postForObject("http://localhost:8080/test/updateUser", userModel, Account.class);
        Assert.assertNotNull("response account is null!",account);
        System.out.println("========" + account);
    }

    @Test
    public void testRestPost2(){
        UserModel userModel = new UserModel();
        userModel.setUserName("888");
        userModel.setPhoneNumber("18888888888");
        HttpEntity<UserModel> entity = new HttpEntity<>(userModel,headers);
        ResponseEntity<Account> accountResponseEntity = restTemplate.postForEntity("http://localhost:8080/test/updateUser", entity, Account.class);
        System.out.println(accountResponseEntity.getBody());
    }

    @Test
    public void testRestPost3(){
        Map<String,Object> mapData = new HashMap<String,Object>(){
            {
                put("id","1");
                put("name","xiaoming");
                put("phoneNumber","18467890213");
                put("age",20);
            }
        };
        Account account = restTemplate.postForObject("http://localhost:8080/test/updateUserMap", mapData, Account.class);
        Assert.assertNotNull("account is null!",account);
    }

}
