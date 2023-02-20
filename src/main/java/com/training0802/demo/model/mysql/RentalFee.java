package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblRentalFee")
public class RentalFee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int roomCharge;
    private int bill;
    private int cleanFee;
    private int parkFee;
    private int additionalFee;

    public RentalFee() {
    }

    public RentalFee(String id, int roomCharge, int bill, int cleanFee, int parkFee, int additionalFee) {
        this.id = id;
        this.roomCharge = roomCharge;
        this.bill = bill;
        this.cleanFee = cleanFee;
        this.parkFee = parkFee;
        this.additionalFee = additionalFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(int roomCharge) {
        this.roomCharge = roomCharge;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getCleanFee() {
        return cleanFee;
    }

    public void setCleanFee(int cleanFee) {
        this.cleanFee = cleanFee;
    }

    public int getParkFee() {
        return parkFee;
    }

    public void setParkFee(int parkFee) {
        this.parkFee = parkFee;
    }

    public int getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(int additionalFee) {
        this.additionalFee = additionalFee;
    }
    public int total(){
        int total = 0;
        total =  roomCharge + bill + parkFee + cleanFee + additionalFee;
        return total;
    }
    @Override
    public String toString() {
        return "RentalFee{" +
                "id='" + id + '\'' +
                ", roomCharge=" + roomCharge +
                ", bill=" + bill +
                ", cleanFee=" + cleanFee +
                ", parkFee=" + parkFee +
                ", additionalFee=" + additionalFee +
                '}';
    }
}
