//package com.lynhatkhanh.educationweb.educationweb.model.didnotUse;
//
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//
//import java.util.List;
//
//@Entity
//@Table(name = "user_account")
//public class UserAccount {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "user_name")
//    @Size(min = 1, message = "User name is required")
//    private String userName;
//
//    @Column(name = "password")
//    @Size(min = 1, message = "Password is required")
//    private String password;
//
//    @Column(name = "active")
//    private int active;
//
//    @OneToOne(
//            mappedBy = "userAccount",
//            cascade = CascadeType.ALL)
//    private UserDetail userDetail;
//
//    @OneToMany(
//            mappedBy = "userAccount",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Authority> authorities;
//
//    // =============== constructor ===============
//    public UserAccount() {
//    }
//
//    public UserAccount(String userName, String password, int active) {
//        this.userName = userName;
//        this.password = password;
//        this.active = active;
//    }
//
//    // =============== getter / setter ===============
//
//    // =============== convenient method - bidirectional relationship ===============
//    // add role
//
//    // =============== toString() ===============
//
//}
