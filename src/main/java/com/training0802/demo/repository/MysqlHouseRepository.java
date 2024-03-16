package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MysqlHouseRepository extends JpaRepository<House, Long> {
    House findByName(String name);
    Optional<House> findByImage(String image);
}
