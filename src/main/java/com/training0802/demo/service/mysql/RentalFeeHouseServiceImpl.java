package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.RentalFeeHouseResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.RentalFeeHouse;
import com.training0802.demo.repository.RentalFeeHouseRepository;
import com.training0802.demo.service.RentalFeeHouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("mysql")
public class RentalFeeHouseServiceImpl implements RentalFeeHouseService {
    @Autowired
    public RentalFeeHouseRepository rentalFeeHouseRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<RentalFeeHouseResponse> getListRentalFeeHouse() {
        List<RentalFeeHouse> mysqlModelRentalHouses = rentalFeeHouseRepository.findAll();
        List<RentalFeeHouseResponse> dtoRentalFeeHouses = new ArrayList<RentalFeeHouseResponse>();
        for (RentalFeeHouse rentalFeeHouse : mysqlModelRentalHouses){
            //convert model to dto
            RentalFeeHouseResponse dtoRentalFeeHouse = modelMapper.map(rentalFeeHouse,RentalFeeHouseResponse.class);
            dtoRentalFeeHouses.add(dtoRentalFeeHouse);
        }
        return dtoRentalFeeHouses;
    }

    @Override
    public RentalFeeHouseResponse getRentalFeeHouseDetail(Long id) {
        RentalFeeHouse modelRentalFeeHouse = rentalFeeHouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found this rental fee house with id: " + id));
        RentalFeeHouseResponse dtoRentalFeeHouse = modelMapper.map(modelRentalFeeHouse,RentalFeeHouseResponse.class);
        return dtoRentalFeeHouse;
    }

    @Override
    public RentalFeeHouseResponse addRetalFeeHouse(RentalFeeHouseResponse rentalFeeHouseResponse) {
        RentalFeeHouse modelRentalFeeHouse = modelMapper.map(rentalFeeHouseResponse, RentalFeeHouse.class);
        rentalFeeHouseRepository.save(modelRentalFeeHouse);
        return rentalFeeHouseResponse;
    }

    @Override
    public void deleteRentalFeeHouse(Long id) {
        rentalFeeHouseRepository.deleteById(id);
    }

    @Override
    public RentalFeeHouseResponse updateRentalFeeHouse(RentalFeeHouseResponse rentalFeeHouseResponse, Long id) {
        RentalFeeHouse rentalFeeHouseById = rentalFeeHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found rental fee house with id: " +id));

        rentalFeeHouseById.setId(id);
        rentalFeeHouseById.setHouse(rentalFeeHouseById.getHouse());
        rentalFeeHouseById.setType(rentalFeeHouseResponse.getType());
        rentalFeeHouseById.setPrice(rentalFeeHouseResponse.getPrice());
        rentalFeeHouseById.setUnit(rentalFeeHouseResponse.getUnit());

        rentalFeeHouseRepository.save(rentalFeeHouseById);
        return rentalFeeHouseResponse;
    }
}
