package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.House;

import java.util.List;

public interface HouseService {
    List<HouseResponse> getHouses();
}
