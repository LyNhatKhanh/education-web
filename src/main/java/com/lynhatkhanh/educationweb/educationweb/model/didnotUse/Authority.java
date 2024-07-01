//package com.lynhatkhanh.educationweb.educationweb.model.didnotUse;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Table(name = "authority")
///* TODO: add UniqueConstraint*/
//public class Authority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "role_name")
//    @Size(min = 1, message = "Role's name is required")
//    private String roleName;
//
//    @Column(name = "user_name")
//    @ManyToOne(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
//                    CascadeType.MERGE, CascadeType.PERSIST}
//    )
//    @JoinColumn(name = "user_account_id")
//    private UserAccount userAccount;
//
//    // =============== constructor ===============
//    public Authority() {
//    }
//
//    public Authority(String roleName, UserAccount userAccount) {
//        this.roleName = roleName;
//        this.userAccount = userAccount;
//    }
//
//    // =============== getter / setter ===============
//
//    // =============== convenient method - bidirectional relationship ===============
//
//
//    // =============== toString() ===============
//
//}
