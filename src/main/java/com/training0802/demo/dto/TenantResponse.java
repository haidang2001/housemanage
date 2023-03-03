package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TenantResponse {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String gender;
    private String phone;
    private String email;
    private int idNumber;
    private String permanentAddress;

    private House house;
    //    @ManyToOne()
//    @JsonBackReference
//    @JoinColumn(name = "room_id")
//    private Room room;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date rentDate;
    private String status;
    private String description;

    public TenantResponse() {
    }

    public TenantResponse(Long id, String name, Date birthDate, String gender, String phone, String email, int idNumber, String permanentAddress, House house,  Date rentDate, String status, String description) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.idNumber = idNumber;
        this.permanentAddress = permanentAddress;
        this.house = house;
//        this.room = room;
        this.rentDate = rentDate;
        this.status = status;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
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
}
