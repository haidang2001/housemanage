package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.model.Account;
import com.training0802.demo.repository.AccountRepositori;
//import com.training0802.demo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    public AccountRepositori accountRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<AccountResponse> getAccounts() {
        //convert raw data(model) to dto
        List<Account> rawAccountList = accountRepository.findAll();
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
        for(Account acc: rawAccountList){
            AccountResponse dto = modelMapper.map(acc,AccountResponse.class); //map từng object trong list
            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public AccountResponse getOneAccount(Long id){
        //java8
//        return accountList.stream().filter(e-> e.getName().equals(name)).findFirst().get();

        //convert raw data to dto
        Account modelAccount = accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Not found account with this id"));
        AccountResponse dtoAccount = modelMapper.map(modelAccount,AccountResponse.class);

        return dtoAccount;
    }

    @Override
    public void addAccount(AccountResponse account){
        //convert dto to model
        // repo.add(Account)
        Account modelAccount = modelMapper.map(account,Account.class);
        accountRepository.save(modelAccount);


    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void updateAccount(AccountResponse account,Long id) {
        //convert dto to model
        //repo.update(Account)
        Account modelAccount = modelMapper.map(account,Account.class);
//        accountRepository.updateAccount(modelAccount,name);

        Account accountByName = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found acount with this id"));
        accountByName.setId(id);
        accountByName.setName(modelAccount.getName());
        accountByName.setGender(modelAccount.getGender());
        accountByName.setRole(modelAccount.getRole());
        accountByName.setPhone(modelAccount.getPhone());
        accountByName.setEmail(modelAccount.getEmail());
        accountRepository.save(accountByName);
    }


}
