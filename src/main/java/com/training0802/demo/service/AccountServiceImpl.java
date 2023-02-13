package com.training0802.demo.service;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.DemoResponse;
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
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    public AccountRepositori accountRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<AccountResponse> getAccounts() {
        //convert raw data(model) to dto
        List<Account> rawAccountList = accountRepository.findAll(); //debug chỗ nào thì thêm tíc đỏ rồi debug ấn f8 xong thì resume
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
        for(Account acc: rawAccountList){
            AccountResponse dto = modelMapper.map(acc,AccountResponse.class); //map từng object trong list
            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public ResponseEntity<DemoResponse> getOneAccount(Long id){
        //java8
//        return accountList.stream().filter(e-> e.getName().equals(name)).findFirst().get();

        //convert raw data to dto
        Optional<Account> modelAccount = accountRepository.findById(id);
        AccountResponse dtoAccount = modelMapper.map(modelAccount,AccountResponse.class);
        if(modelAccount.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DemoResponse(0,"Account founded",modelAccount)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DemoResponse(1,"Can not found account: "+id,"")
            );
        }

//        return dtoAccount;
    }

    @Override
    public ResponseEntity<DemoResponse> addAccount(AccountResponse account){

        //convert dto to model
        // repo.add(Account)
        Account modelAccount = modelMapper.map(account,Account.class);
        accountRepository.save(modelAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new DemoResponse(0,"Add new account successfully",modelAccount)
        );

    }

    @Override
    public void deleteAccount(Long id) {

        accountRepository.deleteById(id);

    }

    @Override
    public void updateAccount(AccountResponse account,String name) {

        //convert dto to model
        //repo.update(Account)
        Account modelAccount = modelMapper.map(account,Account.class);
//        accountRepository.updateAccount(modelAccount,name);

        Account accountByName = accountRepository.findByName(name);
//        accountByName.setId(modelAccount.getId());
        accountByName.setName(modelAccount.getName());
        accountByName.setGender(modelAccount.getGender());
        accountByName.setRole(modelAccount.getRole());
        accountByName.setPhone(modelAccount.getPhone());
        accountByName.setEmail(modelAccount.getEmail());
        accountRepository.save(accountByName);
    }


}
