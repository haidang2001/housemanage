package com.training0802.demo.repository;

import com.training0802.demo.model.mongo.House;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoHouseRepository extends MongoRepository<House,Long> {

}
