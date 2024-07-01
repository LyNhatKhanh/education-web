//package com.lynhatkhanh.educationweb.educationweb.model.didnotUse;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "user_detail")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class UserDetail {
//
//    // =========== define fields ===========
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "first_name")
//    @Size(min = 1, message = "First name is required")
//    private String firstName;
//
//    @Column(name = "last_name")
//    @Size(min = 1, message = "Last name is required")
//    private String lastName;
//
//    @Column(name = "email")
//    @Size(min = 1, message = "Email is required")
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
//    @OneToOne(
//            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
//                    CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "user_account_id")
//    private UserAccount userAccount;
//
//    // =============== constructor ===============
//
//    public UserDetail() {
//    }
//
//    public UserDetail(String firstName, String lastName, String email, Timestamp createDate, Timestamp modifiedDate, String createBy, String modifiedBy) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.createDate = createDate;
//        this.modifiedDate = modifiedDate;
//        this.createBy = createBy;
//        this.modifiedBy = modifiedBy;
//    }
//
//    public UserDetail(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//
//    // =============== getter / setter ===============
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
//    public UserAccount getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(UserAccount userAccount) {
//        this.userAccount = userAccount;
//    }
//
//
//    // =============== toString() ===============
//
//    @Override
//    public String toString() {
//        return "UserDetail{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", createDate=" + createDate +
//                ", modifiedDate=" + modifiedDate +
//                ", createBy='" + createBy + '\'' +
//                ", modifiedBy='" + modifiedBy + '\'' +
//                ", userAccount=" + userAccount +
//                '}';
//    }
//}
