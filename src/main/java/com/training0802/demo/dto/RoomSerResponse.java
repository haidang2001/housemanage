package com.training0802.demo.dto;

import com.training0802.demo.model.mysql.Room;
public class RoomSerResponse {
    private String id;
    private String name;
    private Room room;

    public RoomSerResponse() {
    }

    public RoomSerResponse(String id, String name, Room room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

