package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;

import java.util.List;

public interface HouseService {
    List<HouseResponse> getHouses();

    HouseResponse getHouseDetail(Long id);

    HouseResponse addHouse(HouseResponse houseResponse);

    void deleteHouse(Long id);

    HouseResponse updateHouse(HouseResponse houseResponse, Long id);
}
