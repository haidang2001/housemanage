package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    Optional<Account> findByUsername(String username);

}
