package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "tblHouse")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "tblHouse")
    private List<Room> roomList;
    public House(){}

    public House(Long id, String address) {
        this.id = id;
        this.address = address;

    }

    public House(Long id, String address, List<Room> roomList) {
        this.id = id;
        this.address = address;
        this.roomList = roomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return roomList;
    }

    public void setRooms(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", roomList=" + roomList +
                '}';
    }
}
