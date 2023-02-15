package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.House;
import com.training0802.demo.repository.MoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HouseServiceImp implements HouseService{
    @Autowired
    private MoRepository moRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<HouseResponse> getHouses() {
        List<House> modelHouseData= moRepository.findAll();
        List<HouseResponse> dtoHouseData = new ArrayList<HouseResponse>();
        for(House h: modelHouseData){
            HouseResponse dtoHouse = modelMapper.map(h,HouseResponse.class);
            dtoHouseData.add(dtoHouse);
        }
        return dtoHouseData;
    }
}
