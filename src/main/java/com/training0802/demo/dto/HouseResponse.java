package com.training0802.demo.dto;

import org.bson.types.ObjectId;

public class HouseResponse {
    private String id;
    private String address;


    public HouseResponse(){}

    public HouseResponse(String address) {
        this.address = address;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
