package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlHouseRepository extends JpaRepository<House, Long> {
}
