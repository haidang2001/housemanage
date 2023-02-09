package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.DemoResponse;
import com.training0802.demo.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class HomeController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/")
    public List<AccountResponse> getListAccount(){
        return accountService.getAccounts();
    }

    @GetMapping("/{userName}")
    public AccountResponse getAccount(@PathVariable("userName") String name){
        return accountService.getOneAccount(name);
    }

    @PostMapping("/")
    public void addAccount(@RequestBody AccountResponse accountResponse){
        accountService.addAccount(accountResponse);
    }

    @DeleteMapping("/{userName}")
    public void deleteAccount(@PathVariable("userName") String name){
        accountService.deleteAccount(name);
    }

    @PutMapping("/{userName}")
    public void updateAccount(@RequestBody AccountResponse accountResponse,@PathVariable("userName") String name){
        accountService.updateAccount(accountResponse,name);
    }

}
