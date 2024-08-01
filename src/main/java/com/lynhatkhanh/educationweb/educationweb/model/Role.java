package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "role")
    private Set<UserRole> roleUser;

    // =============== constructor ===============

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // =============== getter / setter ===============

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Set<UserRole> roleUser) {
        this.roleUser = roleUser;
    }


    // =============== convenient method - bidirectional relationship ===============


    // =============== toString() ===============



}
