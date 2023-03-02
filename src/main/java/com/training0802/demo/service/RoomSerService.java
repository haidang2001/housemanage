package com.training0802.demo.service;

import com.training0802.demo.dto.RoomSerResponse;

import java.util.List;

public interface RoomSerService {
    List<RoomSerResponse> getListRoomService();
    RoomSerResponse getDetailRoomService(Long id);
    void addRoomService(RoomSerResponse roomSerResponse);
    void deleteRoomService(Long id);
    RoomSerResponse updateRoomService(RoomSerResponse RoomSerResponse, Long id);

}
