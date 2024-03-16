package com.training0802.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
public class AccResponse {
    private Long id;
    @NotEmpty(message = "Not empty username")
    private String username;
    @Size(min = 6,message = "Password too short, at least 6 letter")
    @NotEmpty(message = "Not empty password")
    private String password;
//    @Size(min = 6,message = "Re Password too short, at least 6 letter")
//    @NotEmpty(message = "Not empty re password")
//    private String repassword;
    private String role;

    public AccResponse() {

    }

    public AccResponse(Long id, String username, String password,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

//    public AccResponse(Long id, String username, String password, String repassword, String role) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.repassword = repassword;
//        this.role = role;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public String getRepassword() {
//        return repassword;
//    }
//
//    public void setRepassword(String repassword) {
//        this.repassword = repassword;
//    }
//
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
