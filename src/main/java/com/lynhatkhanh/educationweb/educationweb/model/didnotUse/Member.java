//package com.lynhatkhanh.educationweb.educationweb.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "member")
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "user_id")
//    @Size(min = 1, message = "User name is required")
//    private String userId;
//
//    @Column(name = "password")
//    @Size(min = 1, message = "User name is required")
//    private String password;
//
//    @Column(name = "active")
//    private int active;
//
//    @OneToOne(
//            mappedBy = "memberId",
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
//                    CascadeType.MERGE, CascadeType.PERSIST})
//    private People peopleId;
//
//    @OneToMany(mappedBy = "memberId")
//    private List<Role> roles;
//
//    public Member() {
//    }
//
//    public Member(String userId, String password, int active) {
//        this.userId = userId;
//        this.password = password;
//        this.active = active;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }
//
//    public People getPeopleId() {
//        return peopleId;
//    }
//
//    public void setPeopleId(People peopleId) {
//        this.peopleId = peopleId;
//    }
//
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//
//    // add convenient method for bidirectional relationship
//    public void addRole(Role role) {
//        if (roles == null)
//            roles = new ArrayList<>();
//        roles.add(role);
//        role.setMemberId(this);
//    }
//
//    @Override
//    public String toString() {
//        return "Member{" +
//                "userId='" + userId + '\'' +
//                ", password='" + password + '\'' +
//                ", active=" + active +
//                ", peopleId=" + peopleId +
//                '}';
//    }
//}
