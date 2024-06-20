package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member memberId;

    @Column(name = "role")
    private String role;

    public Role(Member memberId, String role) {
        this.memberId = memberId;
        this.role = role;
    }

    public Role() {

    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "memberId=" + memberId +
                ", role='" + role + '\'' +
                '}';
    }
}
