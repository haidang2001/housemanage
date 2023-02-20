package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblRoomService")
public class RoomService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String foodDelivery;
    private String houseCleaning;

}
