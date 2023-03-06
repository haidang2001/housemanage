package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.House;
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

@Service
@Profile("mysql")
public class MysqlHouseServiceImpl implements HouseService {
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<HouseResponse> getHouses() {
        List<House> mysqlModelHouses = mysqlHouseRepository.findAll();
        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
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
        mysqlHouseRepository.save(modelHouse);
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

        houseById.setId(id);
        houseById.setLocation(houseResponse.getLocation());
        houseById.setName(houseResponse.getName());
        houseById.setEstablishDate(houseResponse.getEstablishDate());
        houseById.setTotalRooms(houseResponse.getTotalRooms());
        houseById.setManager(houseResponse.getManager());
        houseById.setStatus(houseResponse.getStatus());
        houseById.setDescription(houseResponse.getDescription());
        houseById.setRoomList(houseResponse.getRoomList());

        mysqlHouseRepository.save(houseById);
        return houseResponse;
    }

}
