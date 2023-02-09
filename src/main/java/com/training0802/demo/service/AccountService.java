package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {
    public List<AccountResponse> getAccounts();
    public AccountResponse getOneAccount(String name);
    public void addAccount(AccountResponse account);
    public void deleteAccount(String name);
    public void updateAccount(AccountResponse account, String name);
}
