package com.training0802.demo.repository;

import com.training0802.demo.model.House;
import com.training0802.demo.model.MysqlHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlHouseRepository extends JpaRepository<MysqlHouse,Long> {
}
