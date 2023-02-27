package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/account")

public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping()
    @PreAuthorize("hasAuthority('admin')")
    public List<AccountResponse> getListAccount(){
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public  ResponseEntity<MessageResponse> getAccount(@PathVariable Long id){
        try {
            AccountResponse accountFound = accountService.getOneAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Account found with id: "+id,accountFound)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> addAccount(@RequestBody AccountResponse accountResponse){
        accountService.addAccount(accountResponse);
        HttpHeaders headersne = new HttpHeaders();
        headersne.add("Content-Type", "application/json; charset=utf-8");
        return ResponseEntity.status(HttpStatus.OK).headers(headersne).body(
                new MessageResponse(0,"Add successful account",accountResponse)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteAccount(@PathVariable Long id){
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete account successfully with id:" + id, "")
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,"Not found account with this id: "+id,"")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateAccount(@RequestBody AccountResponse accountResponse, @PathVariable Long id){
        try{
            accountService.updateAccount(accountResponse,id);
            accountResponse.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Update account successfully with id: "+id,accountResponse)
            );
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
    @PutMapping("changeone/{id}")
    public ResponseEntity<MessageResponse> changeOneInAccount(@RequestBody String name,@PathVariable Long id){
        accountService.updateOneChange(name,id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Update name of account with id: "+ id, name)
        );
    }
}
