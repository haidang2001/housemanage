package com.training0802.demo.service;

import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.Room;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getRooms();
    RoomResponse getDetailRoom(Long id);
    void addRoom(RoomResponse roomResponse);
    void deleteRoom(Long id);
    RoomResponse updateRoom(RoomResponse roomResponse, Long id);
}
