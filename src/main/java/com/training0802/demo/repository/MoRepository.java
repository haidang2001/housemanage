package com.training0802.demo.repository;

import com.training0802.demo.model.Account;
import com.training0802.demo.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoRepository extends MongoRepository<House,Long> {

}
