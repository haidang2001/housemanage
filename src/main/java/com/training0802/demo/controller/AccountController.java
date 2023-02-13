package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.DemoResponse;
import com.training0802.demo.model.Account;
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
//    @GetMapping("/{userName}")
//    public  AccountResponse getAccount(@PathVariable("userName") String name){
//        return accountService.getOneAccount(name);
//    }
    @GetMapping("/{id}")
    public  ResponseEntity<DemoResponse> getAccount(@PathVariable Long id){
        return accountService.getOneAccount(id);
    }
//    @PostMapping
//    public ResponseEntity<AccountResponse> addAccount(@RequestBody AccountResponse accountResponse){
//            accountService.addAccount(accountResponse);
//        return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<DemoResponse> addAccount(@RequestBody AccountResponse accountResponse){
        return accountService.addAccount(accountResponse);
//        return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
//        accountService.deleteAccount(id);
//        return new ResponseEntity<String>("Deleted succesfully " + id,HttpStatus.OK);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DemoResponse> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new DemoResponse(0,"Delete account successfully with id:" +id,"")
        );
    }
    @PutMapping("/{userName}")
    public ResponseEntity<DemoResponse> updateAccount(@RequestBody AccountResponse accountResponse,@PathVariable("userName") String name){
        accountService.updateAccount(accountResponse,name);
        return ResponseEntity.status(HttpStatus.OK).body(
                new DemoResponse(0,"Update account successfully with name:"+name,accountResponse)
        );
    }

//    @PutMapping("/{userName}")
//    public ResponseEntity<DemoResponse> updateAccount(@RequestBody AccountResponse accountResponse,@PathVariable("userName") String name){
//        accountService.updateAccount(accountResponse,name);
//        return new ResponseEntity<DemoResponse>(new DemoResponse(0,"Update account successfully with name:"+name,accountResponse),HttpStatus.OK);
//    }

}
