package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.DemoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/")
    public Map homepage(){
        Map<String, String> res = new HashMap<>();
        res.put("key", "anystring");
        return res;
    }
    @GetMapping("/demo")
    public DemoResponse demopage(){
        DemoResponse res = new DemoResponse();
        res.setKey("value");
        return res;
    }

    @GetMapping("/api/account")
    public List<AccountResponse> getListAccount(){
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();

        AccountResponse account = new AccountResponse();
        account.setName("Dang");
        account.setEmail("nchdang@gmail.com");
        account.setGender("male");
        account.setRole("new commer");
        account.setPhone("0395674152");
        accountList.add(account);

        AccountResponse account2 = new AccountResponse();
        account2.setName("hai");
        account2.setEmail("hai@gmail.com");
        account2.setGender("female");
        account2.setRole("new");
        account2.setPhone("0908665405");
        accountList.add(account2);
        return accountList;
    }

}
