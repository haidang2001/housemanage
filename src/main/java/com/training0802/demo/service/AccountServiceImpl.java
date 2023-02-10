package com.training0802.demo.service;

import com.training0802.demo.model.Account;
import com.training0802.demo.repository.AccountRepositori;
//import com.training0802.demo.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    public AccountRepositori accountRepository;
    @Autowired
    public ModelMapper modelMapper;
//    @Override
//    public List<AccountResponse> getAccounts() {
//        //convert raw data to dto
//        List<Account> rawAccountList = accountRepository.findAll(); //debug chỗ nào thì thêm tíc đỏ rồi debug ấn f8 xong thì resume
//        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
//        for(Account acc: rawAccountList){
//            AccountResponse dto = modelMapper.map(acc,AccountResponse.class); //map tung object trong list
//            accountList.add(dto);
//        }
//        return accountList;
//    }
//
//    @Override
//    public AccountResponse getOneAccount(String name){
//        //java8
////        return accountList.stream().filter(e-> e.getName().equals(name)).findFirst().get();
//
//        //convert raw data to dto
//        Account modelAccount = accountRepository.findOne(name);
//        AccountResponse dtoAccount = modelMapper.map(modelAccount,AccountResponse.class);
//        return dtoAccount;
//    }
//
//    @Override
//    public void addAccount(AccountResponse account){
//
//        //convert dto to model
//        // repo.add(Account)
//        Account modelAccount = modelMapper.map(account,Account.class);
//        accountRepository.addAccount(modelAccount);
//
//    }
//
//    @Override
//    public void deleteAccount(String name) {
//
//        accountRepository.deleteAccount(name);
//
//    }
//
//    @Override
//    public void updateAccount(AccountResponse account,String name) {
//
//        //convert dto to model
//        //repo.update(Account)
//        Account modelAccount = modelMapper.map(account,Account.class);
//        accountRepository.updateAccount(modelAccount,name);
//    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findOne(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

}
