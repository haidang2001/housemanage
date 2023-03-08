package com.training0802.demo.model.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tblRoomSer")
public class RoomSer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "room_id")

    @ManyToMany(mappedBy = "roomSers")
    @JsonBackReference
    private List<Room> roomList;

    public RoomSer() {
    }

    public RoomSer(Long id, String name, List<Room> roomList) {
        this.id = id;
        this.name = name;
        this.roomList = roomList;
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
    public List<Room> getRoom() {
        return roomList;
    }

    public void setRoom(List<Room> roomList) {
        this.roomList = roomList;
    }
}
