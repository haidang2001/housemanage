package com.training0802.demo.service;

import com.training0802.demo.dto.AccResponse;
import com.training0802.demo.dto.AccountResponse;

import java.util.List;

public interface AccService {
    List<AccResponse> getAcc();

    AccResponse getOneAcc(Long id);

    AccResponse addAcc(AccResponse account);

//    void deleteAccount(Long id);
//
//    AccountResponse updateAccount(AccountResponse account, Long id);
}
