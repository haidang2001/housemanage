package com.training0802.demo.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tblAcc")
public class Acc {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "yourSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yourSequenceGenerator")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
//    private String repassword;
    private String role;
    @OneToOne(mappedBy = "acc")
    private Account account;

    public Acc() {
    }

    public Acc(Long id, String username, String password, Account account,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.account = account;
        this.role = role;
    }

//    public Acc(Long id, String username, String password, String repassword, String role) {
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
