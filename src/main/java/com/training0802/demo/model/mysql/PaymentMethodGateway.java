package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblPaymentMethodGateway")
public class PaymentMethodGateway {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String name;
    private String qrcode;
    private String TerminalID;
    private String SecretKey;

    public PaymentMethodGateway() {
    }

    public PaymentMethodGateway(Long id, String image, String name, String qrcode, String terminalID, String secretKey) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.qrcode = qrcode;
        TerminalID = terminalID;
        SecretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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
