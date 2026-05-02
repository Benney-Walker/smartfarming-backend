package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.UserStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false,  length = 100)
    private String emailAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(length = 100)
    private String password;

    private LocalDateTime dateOfRegistration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRoles> userRoles;

    @OneToMany(mappedBy = "user")
    private List<Fields> fields;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public Users() {}

    public Users(String userName, String emailAddress, String phoneNumber, String password, LocalDateTime dateOfRegistration, List<UserRoles> userRoles, List<Fields> fields, UserStatus userStatus) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.userRoles = userRoles;
        this.fields = fields;
        this.userStatus = userStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
