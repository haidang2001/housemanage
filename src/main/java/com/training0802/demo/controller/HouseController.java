package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/house")
public class HouseController {
    @Autowired
    private HouseService houseServiceImp;
    @GetMapping
    public List<HouseResponse> getHouses(){
        return houseServiceImp.getHouses();
    }

}
