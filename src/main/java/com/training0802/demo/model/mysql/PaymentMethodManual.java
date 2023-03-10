package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblPaymentMethodManual")
public class PaymentMethodManual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String status;

    public PaymentMethodManual() {
    }

    public PaymentMethodManual(Long id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
