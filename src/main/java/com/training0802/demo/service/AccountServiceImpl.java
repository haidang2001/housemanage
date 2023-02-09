package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.model.Account;
import com.training0802.demo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public ModelMapper modelMapper;
    List<AccountResponse> accountList = new ArrayList<AccountResponse>();
    @Override
    public List<AccountResponse> getAccounts() {

        //convert raw data to dto
        List<Account> rawAccountList = accountRepository.findAll(); //debug chỗ nào thì thêm tíc đỏ rồi debug ấn f8 xong thì resume

        for(Account acc: rawAccountList){
            AccountResponse dto = modelMapper.map(acc,AccountResponse.class); //map tung object trong list
            accountList.add(dto);
        }
        return accountList;


    }

    @Override
    public AccountResponse getOneAccount(String name){
        List<AccountResponse> listAccounts = getAccounts();
        AccountResponse oneAccount = new AccountResponse();
        for(AccountResponse a: listAccounts){
            if(a.getName().equals(name)){
                oneAccount = a;
            }
        }
        return oneAccount;
//        return accountList.stream().filter(e-> e.getName().equals(name)).findFirst().get();
    }

    @Override
    public void addAccount(AccountResponse account){
        AccountResponse acc = new AccountResponse(account.getName(),account.getGender(),account.getRole(),account.getPhone(),account.getEmail());
        accountList.add(acc);
    }

    @Override
    public void deleteAccount(String name) {
        accountList.removeIf(e->e.getName().equals(name));
    }

    @Override
    public void updateAccount(AccountResponse account,String name) {
        for(AccountResponse acc : accountList){
            if(acc.getName().equals(name)){
                acc.setName(account.getName());
                acc.setGender(account.getGender());
                acc.setRole(account.getRole());
                acc.setPhone(account.getPhone());
                acc.setEmail(account.getEmail());
            }
        }
    }

}
