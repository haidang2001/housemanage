package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.training0802.demo.model.mysql.RentalFeeHouse;
import com.training0802.demo.model.mysql.RentalFeeHouse;
import com.training0802.demo.model.mysql.Room;
import jakarta.persistence.*;
import org.bson.types.ObjectId;

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
    private List<Room> roomList;
    private List<RentalFeeHouse> rentalFeeHouseList;
    public HouseResponse(){}

    public HouseResponse(Long id, String location, String name, String establishDate, String manager, String status, String description,String image, List<Room> roomList) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.establishDate = establishDate;
//        this.totalRooms = totalRooms;
        this.manager = manager;
        this.status = status;
        this.description = description;
        this.image=image;
        this.roomList = roomList;
    }
    public HouseResponse(Long id, String location, String name, String establishDate, String manager, String status, String description) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.establishDate = establishDate;
//        this.totalRooms = totalRooms;
        this.manager = manager;
        this.status = status;
        this.description = description;
        this.roomList = null;
    }

    public HouseResponse(Long id, String location, String name, String establishDate, int totalRooms, String manager, String status, String description, String image, List<Room> roomList, List<RentalFeeHouse> rentalFeeHouseList) {
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
    }
    //    @JsonBackReference
    public List<RentalFeeHouse> getRentalFeeHouseList() {
        return rentalFeeHouseList;
    }

    public void setRentalFeeHouseList(List<RentalFeeHouse> rentalFeeHouseList) {
        this.rentalFeeHouseList = rentalFeeHouseList;
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
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", establishDate='" + establishDate + '\'' +
                ", totalRooms=" + totalRooms +
                ", manager='" + manager + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", roomList=" + roomList +
                '}';
    }
}
