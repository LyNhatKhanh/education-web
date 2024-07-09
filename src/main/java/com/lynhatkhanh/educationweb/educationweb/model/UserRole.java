package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    // =============== constructor ===============

    public UserRole(Role role, UserAccount userAccount) {
        this.role = role;
        this.userAccount = userAccount;
    }

    public UserRole() {
    }
    // =============== getter / setter ===============

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    // =============== convenient method - bidirectional relationship ===============


    // =============== toString() ===============

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role=" + role +
                ", userAccount=" + userAccount +
                '}';
    }
}
