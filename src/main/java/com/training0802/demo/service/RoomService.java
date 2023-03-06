package com.training0802.demo.service;

import com.training0802.demo.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getRooms();

    RoomResponse getDetailRoom(Long id);

    RoomResponse addRoom(RoomResponse roomResponse);

    void deleteRoom(Long id);

    RoomResponse updateRoom(RoomResponse roomResponse, Long id);
}
