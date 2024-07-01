//package com.lynhatkhanh.educationweb.educationweb.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "people")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class People {
//
//    // =========== define fields ===========
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @NotNull(message = "is required")
//    private int id;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "createdate")
//    private Timestamp createDate;
//
//    @Column(name = "modifieddate")
//    private Timestamp modifiedDate;
//
//    @Column(name = "createby")
//    private String createBy;
//
//    @Column(name = "modifiedby")
//    private String modifiedBy;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_name")
//    private Member memberId;
//
//
//    // =========== define constructors ===========
//
//    public People() {
//    }
//
//    public People(String firstName, String lastName, String email, Timestamp createDate, Timestamp modifiedDate, String createBy, String modifiedBy) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.createDate = createDate;
//        this.modifiedDate = modifiedDate;
//        this.createBy = createBy;
//        this.modifiedBy = modifiedBy;
//    }
//
//    public People(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//
//
//    // =========== define getters/setters ===========
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Timestamp getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Timestamp createDate) {
//        this.createDate = createDate;
//    }
//
//    public Timestamp getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(Timestamp modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }
//
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
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
//    // =========== define toString() ===========
//
//
//    @Override
//    public String toString() {
//        return "People{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", createDate=" + createDate +
//                ", modifiedDate=" + modifiedDate +
//                ", createBy='" + createBy + '\'' +
//                ", modifiedBy='" + modifiedBy + '\'' +
//                ", memberId=" + memberId +
//                '}';
//    }
//}
