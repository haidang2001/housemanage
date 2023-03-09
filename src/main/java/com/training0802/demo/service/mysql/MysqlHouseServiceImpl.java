package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.service.HouseService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("mysql")
public class MysqlHouseServiceImpl implements HouseService {
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<HouseResponse> getHouses() {
        List<House> mysqlModelHouses = mysqlHouseRepository.findAll();
        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
//        for (House house: mysqlModelHouses){
//            HouseResponse dtoHouse = new HouseResponse();
//            dtoHouse.setId(house.getId());
//            dtoHouse.setName(house.getName());
//            dtoHouse.setEstablishDate(house.getEstablishDate());
//            dtoHouse.setTotalRooms(house.getTotalRooms());
//            AccountResponse accountResponse = modelMapper.map(house.getManager(),AccountResponse.class);
//            dtoHouse.setManager(accountResponse);
//            dtoHouse.setImage(house.getImage());
//            dtoHouse.setStatus(house.getStatus());
//            dtoHouse.setDescription(house.getDescription());
////            HouseResponse dtoHouse = modelMapper.map(house,HouseResponse.class);
//            dtoHouses.add(dtoHouse);
//        }
        dtoHouses = modelMapper.map(mysqlModelHouses, new TypeToken<List<HouseResponse>>() {}.getType());
        return dtoHouses;
    }

    @Override
    public HouseResponse getHouseDetail(Long id) {
        House modelHouse = mysqlHouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found this house with id: " + id));
        HouseResponse dtoHouse = modelMapper.map(modelHouse, HouseResponse.class);
        return dtoHouse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HouseResponse addHouse(HouseResponse houseResponse) {
        House modelHouse = modelMapper.map(houseResponse, House.class);
        House save = mysqlHouseRepository.save(modelHouse);
        houseResponse.setId(save.getId());
        return houseResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHouse(Long id) {
        mysqlHouseRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found house with id: "+id));
        mysqlHouseRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HouseResponse updateHouse(HouseResponse houseResponse, Long id) {
        House houseById = mysqlHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found house with id: " + id));

        houseById.setName(houseResponse.getName());
        houseById.setLocation(houseResponse.getLocation());
        houseById.setEstablishDate(houseResponse.getEstablishDate());
        houseById.setTotalRooms(houseResponse.getTotalRooms());

//        Account ManagerFoundByName = accountRepository.findByName(houseResponse.getManager());
//        houseById.setManager(ManagerFoundByName);
        Account managerEntity = modelMapper.map(houseResponse.getManager(),Account.class);
        houseById.setManager(managerEntity);
        houseById.setImage(houseResponse.getImage());
        houseById.setStatus(houseResponse.getStatus());
        houseById.setDescription(houseResponse.getDescription());
//        houseById.setRentalFeeHouseList(houseResponse.getRentalFeeHouseList());
//        houseById.setRoomList(houseResponse.getRoomList());

        House save = mysqlHouseRepository.save(houseById);
        houseResponse.setId(save.getId());
        return houseResponse;
    }

}
