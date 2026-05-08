package com.bbquantum.smartfarmingbackend.DTO.UI;

public class LoadAllFields {

    private String fieldId;

    private String fieldName;

    private String location;

    private String status;

    public LoadAllFields(String fieldId, String fieldName, String location, String status) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.location = location;
        this.status = status;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }
}
