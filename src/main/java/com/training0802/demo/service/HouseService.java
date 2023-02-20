package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;

import java.util.List;

public interface HouseService {
    List<HouseResponse> getHouses();
    void addHouse(HouseResponse houseResponse);
    void deleteHouse(Long id);
    void updateHouse(HouseResponse houseResponse, Long id);
}
