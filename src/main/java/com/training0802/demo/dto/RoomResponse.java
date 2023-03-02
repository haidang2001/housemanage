package com.training0802.demo.dto;

import com.training0802.demo.model.mysql.House;

public class RoomResponse {
    private Long id;
    private String name;
    private House house;
    private int floor;
    private int area;
    private String image;
    private String status;
    private String roomSer;
    private int rents;
    private String description;

    public RoomResponse() {
    }
    public RoomResponse(Long id, String name, int floor, int area, String image, String status, String roomSer, int rents, String description) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.area = area;
        this.image = image;
        this.status = status;
        this.roomSer = roomSer;
        this.rents = rents;
        this.description = description;
    }

    public RoomResponse(Long id, String name, House house, int floor, int area, String image, String status, String roomSer, int rents, String description) {
        this.id = id;
        this.name = name;
        this.house = house;
        this.floor = floor;
        this.area = area;
        this.image = image;
        this.status = status;
        this.roomSer = roomSer;
        this.rents = rents;
        this.description = description;
    }


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

    public String getService() {
        return roomSer;
    }

    public void setService(String roomSer) {
        this.roomSer = roomSer;
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
                ", roomService='" + roomSer + '\'' +
                ", rents=" + rents +
                ", description='" + description + '\'' +
                '}';
    }
}
