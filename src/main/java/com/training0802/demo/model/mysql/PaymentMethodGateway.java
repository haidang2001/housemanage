package com.training0802.demo.model.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblGatewayPayment")
public class PaymentMethodGateway {
    private String image;
    private String name;
    private String TerminalID;
    private String SecretKey;

    public PaymentMethodGateway(String image, String name, String terminalID, String secretKey) {
        this.image = image;
        this.name = name;
        TerminalID = terminalID;
        SecretKey = secretKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerminalID() {
        return TerminalID;
    }

    public void setTerminalID(String terminalID) {
        TerminalID = terminalID;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
