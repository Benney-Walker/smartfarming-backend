package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.UserRole;
import jakarta.persistence.*;

@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "Users_userId")
    private Users user;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserRoles() {}

    public UserRoles(Users user, UserRole userRole) {
        this.user = user;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
