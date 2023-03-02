package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblRentalFeeHouse")
public class RentalFeeHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
//    private int roomCharge;
//    private int bill;
//    private int cleanFee;
//    private int parkFee;
//    private int additionalFee;
    private String type;
    private int price;
    private String unit;
    @OneToOne
    @JoinColumn(name="house_id")
    private House house;

    public RentalFeeHouse() {
    }

    public RentalFeeHouse(String id, String type, int price, String unit, House house) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.unit = unit;
        this.house = house;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "RentalFeeHouse{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", house=" + house +
                '}';
    }
}
