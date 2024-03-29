package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.model.mysql.Acc;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.repository.AccRepository;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Profile("mysql")
public class AccountServiceImpl implements AccountService {
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public AccRepository accRepository;
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
//            dto.setAcc_id(acc.getAcc().getId());
//            dto.setHouse_id(acc.getHouse().getId());
//            dto.setHouse_id(acc.getHouse());
            if(acc.getHouse() != null){
                dto.setHouseName(acc.getHouse().getName());
            }else{
                dto.setHouseName(null);
            }

            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public List<AccountResponse> getAccountsManager() {
        List<Account> manager = accountRepository.findAllByPosition("manager");
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
        for (Account acc : manager) {
            AccountResponse dto = modelMapper.map(acc, AccountResponse.class);
//            dto.setAcc_id(acc.getAcc().getId());
//            dto.setHouse_id(acc.getHouse().getId());
//            dto.setHouse_id(acc.getHouse());
            dto.setHouseName(acc.getHouse().getName());
            accountList.add(dto);
        }
        return accountList;
    }
    @Override
    public List<AccountResponse> getListManagers() {
        List<Account> manager = accountRepository.findAllManagerHouseNull();
        List<AccountResponse> accountList = new ArrayList<AccountResponse>();
        for (Account acc : manager) {
            AccountResponse dto = modelMapper.map(acc, AccountResponse.class);
//            dto.setAcc_id(acc.getAcc().getId());
//            dto.setHouse_id(acc.getHouse().getId());
//            dto.setHouse_id(acc.getHouse());
//            dto.setHouseName(acc.getHouse().getName());
            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public AccountResponse getOneAccount(Long id) {
        Account modelAccount = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found account with this id: " + id));
        AccountResponse dtoAccount = modelMapper.map(modelAccount, AccountResponse.class);
//        dtoAccount.setHouse_id(modelAccount.getHouse().getId());
//        dtoAccount.setHouse_id(modelAccount.getHouse());
//        dtoAccount.setAcc_id(modelAccount.getAcc().getId());
        dtoAccount.setHouseName(modelAccount.getHouse().getName());
        return dtoAccount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountResponse addAccount(AccountResponse accountResponse) {
//        accountResponse.setPassword(passwordEncoder.encode(accountResponse.getPassword()));
        Account byEmail = accountRepository.findByEmail(accountResponse.getEmail());
        if(Objects.isNull(byEmail)){
            Account modelAccount = new Account();
//        modelAccount.setId(accountResponse.getId());
            modelAccount.setName(accountResponse.getName());
            modelAccount.setGender(accountResponse.getGender());
            modelAccount.setPhone(accountResponse.getPhone());

            modelAccount.setEmail(accountResponse.getEmail());

            modelAccount.setBirthDate(accountResponse.getBirthDate());
            modelAccount.setDescription(accountResponse.getDescription());
            modelAccount.setIdNumber(accountResponse.getIdNumber());
            modelAccount.setPosition(accountResponse.getPosition());
            modelAccount.setStartedDate(accountResponse.getStartedDate());
            modelAccount.setStatus(accountResponse.getStatus());
            modelAccount.setImage(accountResponse.getImage());

//        House house = mysqlHouseRepository.findById(accountResponse.getHouse_id()).orElseThrow(() -> new EntityNotFoundException("Not found house with this id"));
            House house = mysqlHouseRepository.findByName(accountResponse.getHouseName());
            modelAccount.setHouse(house);
//        modelAccount.setHouse(accountResponse.getHouse_id());
        Acc acc = accRepository.findById(accountResponse.getAcc_id()).orElseThrow(() -> new EntityNotFoundException("Not found acc with this id"));
        modelAccount.setAcc(acc);

            Account save = accountRepository.save(modelAccount);
            accountResponse.setId(save.getId());
            return accountResponse;
        }else{
            throw new RuntimeException("This email existed please another email");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found account with id: " + id));
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        Account save = accountRepository.save(accountById);
//        accountResponse.setAcc_id(save.getAcc().getId());
        return accountResponse;
    }
}
