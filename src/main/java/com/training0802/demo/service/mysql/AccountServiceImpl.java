package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.repository.AccountRepository;
//import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mysql")
public class AccountServiceImpl implements AccountService {
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Account modelAccount = accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Not found account with this id: "+id));
        AccountResponse dtoAccount = modelMapper.map(modelAccount,AccountResponse.class);

        return dtoAccount;
    }

    @Override
    public AccountResponse addAccount(AccountResponse account){
        //convert dto to model
        // repo.add(Account)
        account.setPassword(passwordEncoder.encode(account.getPassword()));
//        Account modelAccount = modelMapper.map(account,Account.class);
        Account modelAccount = new Account();
        modelAccount.setId(account.getId());
        modelAccount.setName(account.getName());
        modelAccount.setGender(account.getGender());
        modelAccount.setRole(account.getRole());
        modelAccount.setPhone(account.getPhone());
        modelAccount.setEmail(account.getEmail());
        accountRepository.save(modelAccount);
        return account;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountResponse updateAccount(AccountResponse account,Long id) {
        //convert dto to model
        //repo.update(Account)
//        Account modelAccount = modelMapper.map(account,Account.class);
//        accountRepository.updateAccount(modelAccount,name);

        Account modelAccount = new Account();
        modelAccount.setId(account.getId());
        modelAccount.setName(account.getName());
        modelAccount.setGender(account.getGender());
        modelAccount.setRole(account.getRole());
        modelAccount.setPhone(account.getPhone());
        modelAccount.setEmail(account.getEmail());

        Account accountByName = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found acount with this id:" + id));
        accountByName.setId(id);
        accountByName.setName(modelAccount.getName());
        accountByName.setGender(modelAccount.getGender());
        accountByName.setRole(modelAccount.getRole());
        accountByName.setPhone(modelAccount.getPhone());
        accountByName.setEmail(modelAccount.getEmail());
        accountRepository.save(accountByName);
        return account;
    }

    public void updateOneChange(String name, Long id){
        Account accountById = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found acount with this id:" + id));
        accountById.setName(name);
        accountRepository.save(accountById);

    }

}
