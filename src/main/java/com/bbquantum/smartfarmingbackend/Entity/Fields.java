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

    private String fieldSize;

    @Column(nullable = false)
    private LocalDateTime dateOfRegistration;

    @ManyToOne
    @JoinColumn(name = "User_field")
    private Users user;

    @OneToMany(mappedBy = "field")
    private List<RawSensorData> rawData;

    @OneToMany(mappedBy = "field")
    private List<ModelDecisions> decisions;

    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    public Fields() {}

    public Fields(String fieldId, String fieldName, String fieldLocation, String fieldSize, LocalDateTime dateOfRegistration, Users user, List<RawSensorData> rawData, List<ModelDecisions> decisions, FieldStatus fieldStatus) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.fieldSize = fieldSize;
        this.dateOfRegistration = dateOfRegistration;
        this.user = user;
        this.rawData = rawData;
        this.decisions = decisions;
        this.fieldStatus = fieldStatus;
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

    public String getFieldSize() {
        return fieldSize;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Users getUser() {
        return user;
    }

    public List<RawSensorData> getRawData() {
        return rawData;
    }

    public List<ModelDecisions> getDecisions() {
        return decisions;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }
}
