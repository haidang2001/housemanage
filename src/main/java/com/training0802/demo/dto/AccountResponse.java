package com.training0802.demo.dto;

public class AccountResponse {

    private Long id;
    private String name;
    private String gender;
    private String role;
    private String phone;
    private String email;

    public AccountResponse() {

    }

    public AccountResponse(String name, String gender, String role, String phone, String email) {
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.phone = phone;
        this.email = email;
    }

    public AccountResponse(Long id, String name, String gender, String role, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.phone = phone;
        this.email = email;
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

    @Override
    public String toString() {
        return "AccountResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
