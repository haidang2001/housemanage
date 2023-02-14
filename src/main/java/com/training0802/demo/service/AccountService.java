package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService  {
    List<AccountResponse> getAccounts();
    AccountResponse getOneAccount(Long id);
    void addAccount(AccountResponse account);
    void deleteAccount(Long id);
    void updateAccount(AccountResponse account, Long id);
}
