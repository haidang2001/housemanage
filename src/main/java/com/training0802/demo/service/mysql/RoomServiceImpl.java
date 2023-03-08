package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.model.mysql.RoomSer;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.repository.RoomRepository;
import com.training0802.demo.repository.RoomSerRepository;
import com.training0802.demo.service.RoomService;
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
import java.util.stream.Collectors;

@Service
@Profile("mysql")
public class RoomServiceImpl implements RoomService {
    @Autowired
    public RoomRepository roomRepository;
    @Autowired
    public RoomSerRepository roomSerRepository;
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<RoomResponse> getRooms() {
        List<Room> modelRoomList = roomRepository.findAll();
        List<RoomResponse> dtoRoomList = new ArrayList<RoomResponse>();
        dtoRoomList = modelMapper.map(modelRoomList,new TypeToken<List<RoomResponse>>(){}.getType());
        return dtoRoomList;
    }

    @Override
    public RoomResponse getDetailRoom(Long id) {
        Room modelRoomFindById = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found room with this id: "+ id));
        RoomResponse dtoRoom = modelMapper.map(modelRoomFindById,RoomResponse.class);
        return dtoRoom;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomResponse addRoom(RoomResponse roomResponse) {
        Long idHouse = roomResponse.getHouse().getId();
        House house = mysqlHouseRepository.findById(idHouse).orElseThrow(() -> new EntityNotFoundException("Not exist house with id: "+idHouse));
        int totalRoom = house.getTotalRooms();
        house.setTotalRooms(totalRoom + 1);
        mysqlHouseRepository.save(house);

        Room modelRoom = modelMapper.map(roomResponse,Room.class);
        roomRepository.save(modelRoom);
        return roomResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoom(Long id) {
        roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found room with id: "+id));
        roomRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomResponse updateRoom(RoomResponse roomResponse, Long id) {
        Room roomById = roomRepository.findById(id).orElseThrow(() -> new RuntimeException  ("Not found room with this id"));

//        roomById.setId(id);
        roomById.setName(roomResponse.getName());
        roomById.setFloor(roomResponse.getFloor());
        roomById.setArea(roomResponse.getArea());
        roomById.setImage(roomResponse.getImage());
        roomById.setStatus(roomResponse.getStatus());
        roomById.setRents(roomResponse.getRents());
        roomById.setDescription(roomResponse.getDescription());
        List<RoomSer> listRoomSer = new ArrayList<RoomSer>();
//        dtoRoomList = modelMapper.map(modelRoomList,new TypeToken<List<RoomResponse>>(){}.getType());
        //convert dto to model
        listRoomSer = modelMapper.map(roomResponse.getRoomSers(),new TypeToken<List<RoomSer>>(){}.getType());
//        roomResponse.getRoomSers() = modelMapper.map(roomResponse.getRoomSers(),listRoomSer);
        roomById.setRoomSers(listRoomSer);
//        roomById.setHouse(roomById.getHouse());
//        if (roomResponse.getRoomSers() != null) {
//            List<RoomSer> oldRoomSers = roomById.getRoomSers();
//            List<Long> newRoomSerIds = roomResponse.getRoomSers().stream().map(RoomSer::getId).collect(Collectors.toList());
//            List<RoomSer> newRoomSers = roomSerRepository.findAllById(newRoomSerIds);
//
//            for (RoomSer roomSer : newRoomSers) {
//                if (!oldRoomSers.contains(roomSer)) {
//                    roomSer.setRoom(roomById);
//                }
//            }
//            roomById.setRoomSers(newRoomSers);
//        } else {
//            roomById.setRoomSers(new ArrayList<>());
//        }


        Room save = roomRepository.save(roomById);
        roomResponse = modelMapper.map(save,RoomResponse.class);
        return roomResponse;
    }

}
