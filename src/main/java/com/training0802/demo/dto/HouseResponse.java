package com.training0802.demo.dto;

import org.bson.types.ObjectId;

public class HouseResponse {
    private String id;
    private String address;
    private int fee;
    private String options;

    public HouseResponse(){}

    public HouseResponse(String address, int fee, String options) {
        this.address = address;
        this.fee = fee;
        this.options = options;
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", fee='" + fee + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
