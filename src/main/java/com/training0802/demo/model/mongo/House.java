package com.training0802.demo.model.mongo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "house" )
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String address;



    public House(){}
    public House(String address) {
        this.address = address;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
