package com.training0802.demo.service;

import com.training0802.demo.dto.RentalFeeHouseResponse;

import java.util.List;

public interface RentalFeeHouseService {
    List<RentalFeeHouseResponse> getListRentalFeeHouse();

    RentalFeeHouseResponse getRentalFeeHouseDetail(Long id);

    RentalFeeHouseResponse addRetalFeeHouse(RentalFeeHouseResponse rentalFeeHouseResponse);

    void deleteRentalFeeHouse(Long id);

    RentalFeeHouseResponse updateRentalFeeHouse(RentalFeeHouseResponse rentalFeeHouseResponse, Long id);

}
