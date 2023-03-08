package com.training0802.demo.dto;

import com.training0802.demo.model.mysql.RentalFeeHouse;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.model.mysql.Tenant;

import java.util.List;

public class HouseResponse {
    private Long id;
    private String location;
    private String name;
    private String establishDate;
    private int totalRooms;
    private String manager;
    private String status;
    private  String description;
    private String image;

    private List<RoomResponse> roomList;
    private List<RentalFeeHouseResponse> rentalFeeHouseList;
    private List<TenantResponse> tenantList;

    public HouseResponse() {
    }

    public HouseResponse(Long id, String location, String name, String establishDate, int totalRooms, String manager, String status, String description, String image, List<RoomResponse> roomList, List<RentalFeeHouseResponse> rentalFeeHouseList, List<TenantResponse> tenantList) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.establishDate = establishDate;
        this.totalRooms = totalRooms;
        this.manager = manager;
        this.status = status;
        this.description = description;
        this.image = image;
        this.roomList = roomList;
        this.rentalFeeHouseList = rentalFeeHouseList;
        this.tenantList = tenantList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoomResponse> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomResponse> roomList) {
        this.roomList = roomList;
    }

    public List<RentalFeeHouseResponse> getRentalFeeHouseList() {
        return rentalFeeHouseList;
    }

    public void setRentalFeeHouseList(List<RentalFeeHouseResponse> rentalFeeHouseList) {
        this.rentalFeeHouseList = rentalFeeHouseList;
    }

    public List<TenantResponse> getTenantList() {
        return tenantList;
    }

    public void setTenantList(List<TenantResponse> tenantList) {
        this.tenantList = tenantList;
    }
}
