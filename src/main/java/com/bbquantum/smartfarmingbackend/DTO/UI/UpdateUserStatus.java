package com.bbquantum.smartfarmingbackend.DTO.UI;

public class UpdateUserStatus {

    private String emailAddress;

    private String status;

    public UpdateUserStatus(String emailAddress, String status) {
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getStatus() {
        return status;
    }
}
