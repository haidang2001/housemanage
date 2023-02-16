package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
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

    @GetMapping("/{id}")
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

    @PostMapping
    public ResponseEntity<MessageResponse> addAccount(@RequestBody AccountResponse accountResponse){
        accountService.addAccount(accountResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Add successful account",accountResponse)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Delete account successfully with id:" +id,"")
        );
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
}
