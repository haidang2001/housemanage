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
    private Long idHouse;
    private Long idRoom;
    private Long idTenant;
    private String creator;
    private Date createdDate;
    private Date closingDate;
    private String paymentMethod;
    private String status;
    private int currentIndexElectricity;
    private int currentIndexWaterBill;
    public InvoiceResponse() {
    }

    public InvoiceResponse(Long id, String type, Long idHouse, Long idRoom, Long idTenant, String creator, Date createdDate, String paymentMethod, String status, int currentIndexElectricity, int currentIndexWaterBill) {
        this.id = id;
        this.type = type;
        this.idHouse = idHouse;
        this.idRoom = idRoom;
        this.idTenant = idTenant;
        this.creator = creator;
        this.createdDate = createdDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.currentIndexElectricity = currentIndexElectricity;
        this.currentIndexWaterBill = currentIndexWaterBill;
    }

    public Long getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(Long idHouse) {
        this.idHouse = idHouse;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
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


    public Long getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(Long idTenant) {
        this.idTenant = idTenant;
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
