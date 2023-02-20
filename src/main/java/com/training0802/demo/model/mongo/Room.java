package com.training0802.demo.model.mongo;

public class Room {
    private String id;
    private String name;
    private String options;
    private String image;
    private int rentalFee;

    public Room() {
    }

    public Room(String id, String name, String options, String image, int rentalFee) {
        this.id = id;
        this.name = name;
        this.options = options;
        this.image = image;
        this.rentalFee = rentalFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(int rentalFee) {
        this.rentalFee = rentalFee;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", options='" + options + '\'' +
                ", image='" + image + '\'' +
                ", rentalFee=" + rentalFee +
                '}';
    }
}
