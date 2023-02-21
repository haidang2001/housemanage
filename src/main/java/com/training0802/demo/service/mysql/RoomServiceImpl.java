package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.repository.RoomRepository;
import com.training0802.demo.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mysql")
public class RoomServiceImpl implements RoomService {
    @Autowired
    public RoomRepository roomRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<RoomResponse> getRooms() {
        List<Room> modelRoomList = roomRepository.findAll();
        List<RoomResponse> dtoRoomList = new ArrayList<RoomResponse>();
        for (Room room: modelRoomList){
            RoomResponse roomResponse = modelMapper.map(room,RoomResponse.class);
            dtoRoomList.add(roomResponse);
        }
        return dtoRoomList;
    }

    @Override
    public void addRoom(RoomResponse roomResponse) {
        Room modelRoom = modelMapper.map(roomResponse,Room.class);
        roomRepository.save(modelRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void updateRoom(RoomResponse roomResponse, Long id) {
//        Room modelRoom = modelMapper.map(roomResponse,Room.class);
        Room roomById = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("not found room with this id"));

        roomById.setId(id);
        roomById.setName(roomResponse.getName());
        roomById.setHouse(roomById.getHouse());
        roomById.setFloor(roomResponse.getFloor());
        roomById.setArea(roomResponse.getArea());
        roomById.setImage(roomResponse.getImage());
        roomById.setStatus(roomResponse.getStatus());
        roomById.setService(roomResponse.getService());
        roomById.setRents(roomResponse.getRents());
        roomById.setDescription(roomResponse.getDescription());

        roomRepository.save(roomById);
    }

}
