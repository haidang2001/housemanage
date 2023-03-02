package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tblInvoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "house_id")
    private House house;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    private String creator;
    private Date createdDate;
    private String paymentMethod;
    private String status;

    public Invoice() {
    }

    public Invoice(Long id, House house, Room room, Tenant tenant, String creator, Date createdDate, String paymentMethod, String status) {
        this.id = id;
        this.house = house;
        this.room = room;
        this.tenant = tenant;
        this.creator = creator;
        this.createdDate = createdDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
