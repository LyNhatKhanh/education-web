//package com.lynhatkhanh.educationweb.educationweb.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "role")
//public class Role {
//
//    @Id
//    @ManyToOne(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.MERGE,
//                    CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "user_id")
//    private Member memberId;
//
//    @Column(name = "role")
//    private String role;
//
//    public Role(Member memberId, String role) {
//        this.memberId = memberId;
//        this.role = role;
//    }
//
//    public Role() {
//
//    }
//
//    public Member getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(Member memberId) {
//        this.memberId = memberId;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "memberId=" + memberId +
//                ", role='" + role + '\'' +
//                '}';
//    }
//}
