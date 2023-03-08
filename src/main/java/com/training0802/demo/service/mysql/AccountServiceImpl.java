package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
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
        accountRepository.findAll();
        List<Account> rawAccountList = accountRepository.findAll();
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
        for (Account acc : rawAccountList) {
            AccountResponse dto = modelMapper.map(acc, AccountResponse.class);
            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public AccountResponse getOneAccount(Long id) {
        Account modelAccount = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found account with this id: " + id));
        AccountResponse dtoAccount = modelMapper.map(modelAccount, AccountResponse.class);

        return dtoAccount;
    }

    @Override
    public AccountResponse addAccount(AccountResponse accountResponse) {
//        accountResponse.setPassword(passwordEncoder.encode(accountResponse.getPassword()));

        Account modelAccount = new Account();
        modelAccount.setId(accountResponse.getId());
        modelAccount.setName(accountResponse.getName());
        modelAccount.setGender(accountResponse.getGender());
//        modelAccount.setRole(accountResponse.getRole());
        modelAccount.setPhone(accountResponse.getPhone());
        modelAccount.setEmail(accountResponse.getEmail());
//        modelAccount.setUsername(accountResponse.getUsername());
//        modelAccount.setPassword(accountResponse.getPassword());
        modelAccount.setBirthDate(accountResponse.getBirthDate());
        modelAccount.setDescription(accountResponse.getDescription());
        modelAccount.setIdNumber(accountResponse.getIdNumber());
        modelAccount.setPosition(accountResponse.getPosition());
        modelAccount.setStartedDate(accountResponse.getStartedDate());
        modelAccount.setStatus(accountResponse.getStatus());
//        modelAccount.setHouse(accountResponse.getHouse());

        Account save = accountRepository.save(modelAccount);
        accountResponse.setId(save.getId());
        return accountResponse;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found account with id: " + id));
        accountRepository.deleteById(id);
    }

    @Override
    public AccountResponse updateAccount(AccountResponse accountResponse, Long id) {
        Account accountById = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found acount with this id:" + id));

//        accountById.setId(id);
        accountById.setName(accountResponse.getName());
        accountById.setGender(accountResponse.getGender());
//        accountById.setRole(accountResponse.getRole());
        accountById.setPhone(accountResponse.getPhone());
        accountById.setEmail(accountResponse.getEmail());
        accountById.setBirthDate(accountResponse.getBirthDate());
        accountById.setDescription(accountResponse.getDescription());
        accountById.setIdNumber(accountResponse.getIdNumber());
        accountById.setPosition(accountResponse.getPosition());
        accountById.setStartedDate(accountResponse.getStartedDate());
        accountById.setStatus(accountResponse.getStatus());
//        accountById.setHouse(accountResponse.getHouse());

        accountRepository.save(accountById);
        return accountResponse;
    }
}
