package com.training0802.demo.model.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tblRentalFeeHouse")
public class RentalFeeHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private int price;
    private String unit;
//    @JsonIgnore
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="house_id")
    private House house;

    public RentalFeeHouse() {
    }

    public RentalFeeHouse(Long id, String type, int price, String unit, House house) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.unit = unit;
        this.house = house;
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
//    @JsonBackReference

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
