package com.training0802.demo.model.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tblInvoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "house_id")
    private House house;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    private String phoneNumber;
    private String email;
    private String creator;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date closingDate;
    private String paymentMethod;
    private String status;
    private int currentIndexElectricity;
    private int currentIndexWaterBill;
    public Invoice() {
    }

    public Invoice( String type, House house, Room room, Tenant tenant, String creator, Date createdDate, String paymentMethod, String status, int currentIndexElectricity, int currentIndexWaterBill) {
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
