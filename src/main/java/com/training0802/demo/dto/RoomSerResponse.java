package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training0802.demo.model.mysql.Room;
import jakarta.persistence.*;

public class RoomSerResponse {
    private Long id;
    private String name;

    private Room room;
    public RoomSerResponse() {
    }

    public RoomSerResponse(Long id, String name, Room room) {
        this.id = id;
        this.name = name;
        this.room = room;
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
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

