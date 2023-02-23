package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity(name="ACCOUNT")
@Table(name="tblAccount")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String role;
    private String phone;
    private String email;

    private String username;
    private String password;
    public Account() {

    }


    public Account( String name, String gender, String role, String phone, String email, String username, String password) {

        this.name = name;
        this.gender = gender;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Account(Long id, String name, String gender, String role, String phone, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
