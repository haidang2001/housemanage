package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.RentalFeeHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalFeeHouseRepository extends JpaRepository<RentalFeeHouse,Long> {
}
