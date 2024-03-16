package com.training0802.demo.service;

import com.training0802.demo.dto.HouseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HouseService {
    List<HouseResponse> getHouses();

    HouseResponse getHouseDetail(Long id);

    HouseResponse addHouse(HouseResponse houseResponse,MultipartFile image) throws IOException;

    void deleteHouse(Long id);

    HouseResponse updateHouse(HouseResponse houseResponse, Long id);
}
