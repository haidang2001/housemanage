package com.training0802.demo.service;

import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImp implements RoomService{
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
        Room modelRoom = modelMapper.map(roomResponse,Room.class);
        Room roomById = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("not found room with this id"));

        roomById.setId(id);
        roomById.setName(modelRoom.getName());
        roomById.setHouse(modelRoom.getHouse());
        roomById.setFloor(modelRoom.getFloor());
        roomById.setArea(modelRoom.getArea());
        roomById.setImage(modelRoom.getImage());
        roomById.setStatus(modelRoom.getStatus());
        roomById.setService(modelRoom.getService());
        roomById.setRents(modelRoom.getRents());
        roomById.setDescription(modelRoom.getDescription());

        roomRepository.save(roomById);
    }

}
