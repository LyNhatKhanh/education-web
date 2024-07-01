package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    @Size(min = 1, message = "User name is required")
    private String userName;

    @Column(name = "password")
    @Size(min = 1, message = "Password is required")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "first_name")
    @Size(min = 1, message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 1, message = "Last name is required")
    private String lastName;

    @Column(name = "gender")
    @Size(min = 1, message = "Gender is required")
    private Boolean gender;

    @Column(name = "birthday")
    @Size(min = 1, message = "Birthday is required")
    private Date birthday;

    @Column(name = "address")
    @Size(min = 1, message = "Address is required")
    private String address;

    @Column(name = "email")
    @Size(min = 1, message = "Email is required")
    private String email;

    @Column(name = "telephone")
    @Size(min = 1, message = "Telephone is required")
    private String telephone;


    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    private Set<UserRole> userRole;

    // =============== constructor ===============
    public UserAccount() {
    }

    public UserAccount(String userName, String password, Boolean enabled, String firstName, String lastName, Boolean gender, Date birthday, String address, String email, String telephone, Set<UserRole> userRole) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.userRole = userRole;
    }

    public UserAccount(String userName, String password, Boolean enabled, String firstName, String lastName, Boolean gender, Date birthday, String address, String email, String telephone) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }

    // =============== getter / setter ===============

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }


    // =============== convenient method - bidirectional relationship ===============


    // =============== toString() ===============

}
