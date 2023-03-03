package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.RoomSer;
import jakarta.persistence.*;

import java.util.List;

public class RoomResponse {
    private Long id;
    private String name;
    private House house;
    private int floor;
    private int area;
    private String image;
    private String status;
    private List<RoomSer> roomSers;
    private int rents;
    private String description;

    public RoomResponse() {
    }

    public RoomResponse(Long id, String name, House house, int floor, int area, String image, String status, List<RoomSer> roomSers, int rents, String description) {
        this.id = id;
        this.name = name;
        this.house = house;
        this.floor = floor;
        this.area = area;
        this.image = image;
        this.status = status;
        this.roomSers = roomSers;
        this.rents = rents;
        this.description = description;
    }
//    public RoomResponse(Long id, String name, int floor, int area, String image, String status, List<RoomSer> roomSers, int rents, String description) {
//        this.id = id;
//        this.name = name;
//        this.floor = floor;
//        this.area = area;
//        this.image = image;
//        this.status = status;
//        this.roomSers = roomSers;
//        this.rents = rents;
//        this.description = description;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RoomSer> getRoomSers() {
        return roomSers;
    }

    public void setRoomSers(List<RoomSer> roomSers) {
        this.roomSers = roomSers;
    }

    public int getRents() {
        return rents;
    }

    public void setRents(int rents) {
        this.rents = rents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", house=" + house +
                ", floor=" + floor +
                ", area=" + area +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", roomService='" + roomSers + '\'' +
                ", rents=" + rents +
                ", description='" + description + '\'' +
                '}';
    }
}
