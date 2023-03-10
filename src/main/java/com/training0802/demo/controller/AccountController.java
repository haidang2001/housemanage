package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")

public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping()
    @PreAuthorize("hasAuthority('admin')")
    public List<AccountResponse> getListAccount() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getAccount(@PathVariable Long id) {
        try {
            AccountResponse accountFound = accountService.getOneAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Account found with id: " + id, accountFound)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/add")

    public ResponseEntity<MessageResponse> addAccount(@RequestBody AccountResponse accountResponse) {
        try {
            AccountResponse accountResponse1 = accountService.addAccount(accountResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add successful account", accountResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }


    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> deleteAccount(@PathVariable Long id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete account successfully with id:" + id, id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> updateAccount(@RequestBody AccountResponse accountResponse, @PathVariable Long id) {
        try {
            AccountResponse accountResponse1 = accountService.updateAccount(accountResponse, id);
            accountResponse.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update account successfully with id: " + id, accountResponse1)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
}
