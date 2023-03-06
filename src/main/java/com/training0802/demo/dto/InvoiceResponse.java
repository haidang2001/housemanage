package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.model.mysql.Tenant;
import jakarta.persistence.*;

import java.util.Date;

public class InvoiceResponse {
    private Long id;
    private String type;
    private House house;
    private Room room;
    private Tenant tenant;
    private String phoneNumber;
    private String email;
    private String creator;
    private Date createdDate;
    private String paymentMethod;
    private String status;
    private int currentIndexElectricity;
    private int currentIndexWaterBill;
    public InvoiceResponse() {
    }

    public InvoiceResponse( String type, House house, Room room, Tenant tenant, String creator, Date createdDate, String paymentMethod, String status, int currentIndexElectricity, int currentIndexWaterBill) {
        this.type = type;
        this.house = house;
        this.room = room;
        this.tenant = tenant;
        this.creator = creator;
        this.createdDate = createdDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.currentIndexElectricity = currentIndexElectricity;
        this.currentIndexWaterBill = currentIndexWaterBill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentIndexElectricity() {
        return currentIndexElectricity;
    }

    public void setCurrentIndexElectricity(int currentIndexElectricity) {
        this.currentIndexElectricity = currentIndexElectricity;
    }

    public int getCurrentIndexWaterBill() {
        return currentIndexWaterBill;
    }

    public void setCurrentIndexWaterBill(int currentIndexWaterBill) {
        this.currentIndexWaterBill = currentIndexWaterBill;
    }
}
