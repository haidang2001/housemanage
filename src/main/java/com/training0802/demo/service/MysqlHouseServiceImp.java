package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.repository.MysqlHouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("mysql")
public class MysqlHouseServiceImp implements HouseService{
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<HouseResponse> getHouses() {
        List<House> mysqlModelHouses = mysqlHouseRepository.findAll();
        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
        for (House house : mysqlModelHouses){
            //convert model to dto
            HouseResponse dtoHouse = modelMapper.map(house,HouseResponse.class);
            dtoHouses.add(dtoHouse);
        }

        return dtoHouses;
    }
}
