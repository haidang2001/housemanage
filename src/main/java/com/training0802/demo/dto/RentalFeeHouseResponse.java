package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training0802.demo.model.mysql.House;
import jakarta.persistence.*;

public class RentalFeeHouseResponse {
    private Long id;
    private String type;
    private int price;
    private String unit;
//    private House house;
    private Long idHouse;

    public RentalFeeHouseResponse() {
    }

    public RentalFeeHouseResponse(Long id, String type, int price, String unit, Long idHouse) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.unit = unit;
        this.idHouse = idHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

//    public House getHouse() {
//        return house;
//    }
//
//    public void setHouse(House house) {
//        this.house = house;
//    }


    public Long getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(Long idHouse) {
        this.idHouse = idHouse;
    }
}
