package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.MysqlHouse;
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
        List<MysqlHouse> mysqlModelHouses = mysqlHouseRepository.findAll();
        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
        for (MysqlHouse mysqlHouse: mysqlModelHouses){
            HouseResponse dtoHouse = modelMapper.map(mysqlHouse,HouseResponse.class);

//            HouseResponse dtoHouse = new HouseResponse();
//            dtoHouse.setId(mysqlHouse.getId());
//            dtoHouse.setAddress(mysqlHouse.getAddress());
//            dtoHouse.setFee(mysqlHouse.getFee());
//            dtoHouse.setOptions(mysqlHouse.getOptions());
            dtoHouses.add(dtoHouse);
        }

        return dtoHouses;
    }
}
