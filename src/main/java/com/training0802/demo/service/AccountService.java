package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.DemoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService  {
    public List<AccountResponse> getAccounts();
    public ResponseEntity<DemoResponse> getOneAccount(Long id);
    public ResponseEntity<DemoResponse> addAccount(AccountResponse account);
    public void deleteAccount(Long id);
    public void updateAccount(AccountResponse account, String name);


}
