package com.training0802.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training0802.demo.model.mysql.House;
import jakarta.persistence.*;

import java.util.Date;

public class AccountResponse {
    private Long id;
    private String name;
    private Date birthDate;
    private String gender;
    //    private String role;
    private String phone;
    private String email;
    private int idNumber;
    private Long house_id;
    private String position;
    //    private String username;
//    private String password;
    private Date startedDate;
    private String status;
    private String description;

    public AccountResponse() {

    }


    public AccountResponse(String name, String gender, String role, String phone, String email, String username, String password) {

        this.name = name;
        this.gender = gender;
//        this.role = role;
        this.phone = phone;
        this.email = email;
//        this.username = username;
//        this.password = password;
    }

    public AccountResponse(Long id, String name, String gender, String role, String phone, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
//        this.role = role;
        this.phone = phone;
        this.email = email;
//        this.username = username;
//        this.password = password;
    }

    public AccountResponse(Long id, String name, Date birthDate, String gender, String role, String phone, String email, int idNumber, Long house_id, String position, String username, String password, Date startedDate, String status, String description) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
//        this.role = role;
        this.phone = phone;
        this.email = email;
        this.idNumber = idNumber;
        this.house_id = house_id;
        this.position = position;
//        this.username = username;
//        this.password = password;
        this.startedDate = startedDate;
        this.status = status;
        this.description = description;
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

//    public String getRole() {
//        return role;
//    }

//    public void setRole(String role) {
//        this.role = role;
//    }

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

    public Long getHouse() {
        return house_id;
    }

    public void setHouse(Long house_id) {
        this.house_id = house_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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
}
