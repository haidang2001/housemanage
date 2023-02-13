package com.training0802.demo.repository;

import com.training0802.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepositori extends JpaRepository<Account,Long> {
    Account findByName(String name);

}
