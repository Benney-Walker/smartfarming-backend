package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.FieldStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Fields {

    @Id
    private String fieldId;

    @Column(nullable = false)
    private String fieldName;

    @Column(nullable = false)
    private String fieldLocation;

    @Column(nullable = false)
    private LocalDateTime dateOfRegistration;

    @ManyToOne
    @JoinColumn
    private Users user;

    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    public Fields() {}

    public Fields(String fieldId, String fieldName, String fieldLocation, LocalDateTime dateOfRegistration, Users user) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.dateOfRegistration = dateOfRegistration;
        this.user = user;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Users getUser() {
        return user;
    }

    public void setFieldStatus(FieldStatus fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }
}
