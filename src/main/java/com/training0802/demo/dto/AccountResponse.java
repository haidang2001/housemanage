package com.training0802.demo.dto;

public class AccountResponse {
    private String name;
    private String gender;
    private String role;
    private String phone;
    private String email;

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

}
