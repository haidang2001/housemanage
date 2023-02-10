package com.training0802.demo.repository;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {
    private List<Account> accountList = new ArrayList<Account>();

    //constructor
    public AccountRepository() {
        mockdata();
    }

    public List<Account> findAll(){
        return accountList; //debug chỗ nào thì thêm tíc đỏ rồi debug ấn f8 xong thì resume
    }

    public Account findOne(String name){
        Account accountFound = new Account();

        for(Account acc: accountList){
            if(acc.getName().equals(name)){
                accountFound.setName(acc.getName());
                accountFound.setGender(acc.getGender());
                accountFound.setRole(acc.getRole());
                accountFound.setPhone(acc.getPhone());
                accountFound.setEmail(acc.getEmail());
            }
        }
        return accountFound;

//        Account accountFoundd = accountList.stream().filter(e->e.getName().equals(name)).findFirst().get();
//        if (accountFound == null){
//            return (ResponseEntity<Account>) ResponseEntity.notFound();
//        }else{
//            return ResponseEntity.ok(accountFound);
//        }
    }

    public void addAccount(Account newAccount){
        accountList.add(newAccount);
    }

    public void deleteAccount(String name){
        accountList.removeIf(e -> e.getName().equals(name));
    }

    public void updateAccount(Account account, String name){
        for (Account acc: accountList){
            if(acc.getName().equals(name)){
                acc.setName(account.getName());
                acc.setGender(account.getGender());
                acc.setRole(account.getRole());
                acc.setPhone(account.getPhone());
                acc.setEmail(account.getEmail());
            }
        }
    }

    public List<Account> mockdata(){
        //List<Account> accountList = new ArrayList<Account>();
        Account account = new Account();
        account.setName("Dang");
        account.setEmail("nchdang@gmail.com");
        account.setGender("male");
        account.setRole("new commer");
        account.setPhone("0395674152");
        this.accountList.add(account); //dùng this để phân biệt với private accountList nếu có đặt thêm accountList trong mockdata

        Account account2 = new Account();
        account2.setName("hai");
        account2.setEmail("hai@gmail.com");
        account2.setGender("female");
        account2.setRole("new");
        account2.setPhone("0908665405");
        this.accountList.add(account2);

        return accountList;

    }
}
