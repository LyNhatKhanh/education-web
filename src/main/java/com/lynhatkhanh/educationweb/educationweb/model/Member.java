package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    @Column(name = "people_id")
    private int peopleId;

    @OneToMany(mappedBy = "memberId")
    private List<Role> roles;

    public Member() {
    }

    public Member(String userId, String password, int active, int peopleId) {
        this.userId = userId;
        this.password = password;
        this.active = active;
        this.peopleId = peopleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public List<Role> getTheRole() {
        return roles;
    }

    public void setTheRole(List<Role> theRole) {
        this.roles = theRole;
    }

    // add convenient method for bidirectional relationship
    public void addRole(Role role) {
        if(roles == null)
            roles = new ArrayList<>();
        roles.add(role);
        role.setMemberId(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", peopleId=" + peopleId +
                '}';
    }
}
