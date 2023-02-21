package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.service.HouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
        for (House house : mysqlModelHouses){
            //convert model to dto
            HouseResponse dtoHouse = modelMapper.map(house,HouseResponse.class);
            house.getTotalRooms();
            dtoHouses.add(dtoHouse);

        }
        return dtoHouses;
    }

    @Override
    public void addHouse(HouseResponse houseResponse){
        House modelHouse = modelMapper.map(houseResponse, House.class);
        mysqlHouseRepository.save(modelHouse);
    }

    @Override
    public void deleteHouse(Long id) {
        mysqlHouseRepository.deleteById(id);
    }

    @Override
    public void updateHouse(HouseResponse houseResponse, Long id) {
//        House modelHouse = modelMapper.map(houseResponse,House.class);
        House houseById = mysqlHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found house with " +id));

        houseById.setId(id);
        houseById.setLocation(houseResponse.getLocation());
        houseById.setName(houseResponse.getName());
        houseById.setEstablishDate(houseResponse.getEstablishDate());
//        houseById.setTotalRooms(houseResponse.getTotalRooms());
        houseById.setManager(houseResponse.getManager());
        houseById.setStatus(houseResponse.getStatus());
        houseById.setDescription(houseResponse.getDescription());
        houseById.setRoomList(houseResponse.getRoomList());

        mysqlHouseRepository.save(houseById);
    }

}
