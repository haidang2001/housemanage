package com.training0802.demo.service;

import com.training0802.demo.dto.AccResponse;
import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.LoginResponse;
import com.training0802.demo.dto.MessageResponse;
import org.aspectj.bridge.Message;

import java.util.List;

public interface AccService {
    List<AccResponse> getAcc();
    AccResponse getLastAcc();

    AccResponse getOneAcc(Long id);

    AccResponse addAcc(AccResponse account);

//    void deleteAccount(Long id);
//
//    AccountResponse updateAccount(AccountResponse account, Long id);

    MessageResponse login(LoginResponse loginResponse);
}
