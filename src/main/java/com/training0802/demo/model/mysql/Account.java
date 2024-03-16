package com.training0802.demo.model.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ACCOUNT")
@Table(name = "tblAccount")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String gender;
    private String phone;
    private String email;
    private int idNumber;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true )
    @JsonBackReference
    @JoinColumn(name = "house_name")
    private House house;
//    private Long house;
    private String position;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startedDate;
    private String status;
    private String description;
    @OneToOne()
    @JoinColumn(name = "acc_id")
    private Acc acc;

    private String image;
    public Account() {

    }


    public Account(String name, String gender, String role, String phone, String email, String username, String password) {

        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public Account(Long id, String name, String gender, String role, String phone, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public Account(Long id, String name, Date birthDate, String gender, String role, String phone, String email, int idNumber, House house, String position, String username, String password, Date startedDate, String status, String description, Acc acc,String image) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.idNumber = idNumber;
        this.house = house;
        this.position = position;
        this.startedDate = startedDate;
        this.status = status;
        this.description = description;
        this.acc = acc;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

//    public Long getHouse() {
//        return house;
//    }
//
//    public void setHouse(Long house) {
//        this.house = house;
//    }


    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
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

    public Acc getAcc() {
        return acc;
    }

    public void setAcc(Acc acc) {
        this.acc = acc;
    }
}
