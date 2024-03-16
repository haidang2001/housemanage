package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Acc;
import com.training0802.demo.model.mysql.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccRepository extends JpaRepository<Acc, Long> {
    Optional<Acc> findByUsername(String username);
    boolean existsByUsername(String username);

    Optional<Acc> findOneByUsernameAndPassword(String username,String password);
    Acc findTopByOrderByIdDesc();

}
