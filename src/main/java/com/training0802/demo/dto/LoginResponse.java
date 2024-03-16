package com.training0802.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginResponse {

    @NotEmpty(message = "Not empty username")
    private String username;
    @Size(min = 6,message = "Password too short, at least 6 letter")
    @NotEmpty(message = "Not empty password")
    private String password;

    public LoginResponse() {

    }

    public LoginResponse( String username, String password) {
        this.username = username;
        this.password = password;
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

}
