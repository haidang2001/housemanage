package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.model.mysql.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String name);
    Account findByEmail(String email);
    Account findByHouse(House house);
    @Query("Select acc from ACCOUNT acc where acc.position='manager' and house is null")
    List<Account> findAllManagerHouseNull();
    List<Account> findAllByPosition(String position);

}
