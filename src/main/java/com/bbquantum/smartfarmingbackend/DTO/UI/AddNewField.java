package com.bbquantum.smartfarmingbackend.DTO.UI;

public class AddNewField {

    private String fieldName;

    private String fieldLocation;

    private int userId;

    public AddNewField(String fieldName, String fieldLocation, int userId) {
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.userId = userId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public int getUserId() {
        return userId;
    }
}
