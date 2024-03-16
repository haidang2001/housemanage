package com.training0802.demo.service.mongodb;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mongo.House;
import com.training0802.demo.repository.MongoHouseRepository;
import com.training0802.demo.service.HouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("mongodb")
public class MongoHouseServiceImpl implements HouseService {
    @Autowired
    private MongoHouseRepository moRepository;
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

    @Override
    public HouseResponse getHouseDetail(Long id) {
        return null;
    }

    @Override
    public HouseResponse addHouse(HouseResponse houseResponse,MultipartFile image) {
        return null;
    }

//    @Override
//    public HouseResponse addHouse(HouseResponse houseResponse) {
//
//        return houseResponse;
//    }

    @Override
    public void deleteHouse(Long id) {

    }

    @Override
    public HouseResponse updateHouse(HouseResponse houseResponse, Long id) {
        return houseResponse;
    }
}
