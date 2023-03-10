package com.training0802.demo.dto;

import com.training0802.demo.model.mysql.Room;

import java.util.List;

public class RoomSerResponse {
    private Long id;
    private String name;

//    private List<RoomResponse> roomList;

    public RoomSerResponse() {
    }

    public RoomSerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
//        this.roomList = roomList;
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
        this.name =  name;
    }

//    public List<RoomResponse> getRoom() {
//        return roomList;
//    }
//
//    public void setRoom(List<RoomResponse> roomList) {
//        this.roomList = roomList;
//    }
}

