package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public List<AccountResponse> getListAccount(){
        return accountService.getAccounts();
    }

    @GetMapping("/{userName}")
    public  AccountResponse getAccount(@PathVariable("userName") String name){
        return accountService.getOneAccount(name);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> addAccount(@RequestBody AccountResponse accountResponse){
        accountService.addAccount(accountResponse);
        return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteAccount(@PathVariable("userName") String name){
        accountService.deleteAccount(name);
        return new ResponseEntity<String>("Deleted succesfully " + name,HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<AccountResponse> updateAccount(@RequestBody AccountResponse accountResponse,@PathVariable("userName") String name){
        accountService.updateAccount(accountResponse,name);
        return new ResponseEntity<AccountResponse>(accountResponse,HttpStatus.OK);
    }

}
