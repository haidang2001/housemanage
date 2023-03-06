package com.training0802.demo.model.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tblRoomSer")
public class RoomSer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "room_id")
    private Room room;

    public RoomSer() {
    }

    public RoomSer(Long id, String name, Room room) {
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

    //    @JsonBackReference
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
