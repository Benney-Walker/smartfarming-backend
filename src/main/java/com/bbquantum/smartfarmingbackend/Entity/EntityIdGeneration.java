package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntityIdGeneration {

    @Id
    private String entityName;

    private int entityCode;

    public EntityIdGeneration() {}

    public EntityIdGeneration(String entityName, int entityCode) {
        this.entityName = entityName;
        this.entityCode = entityCode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(int entityCode) {
        this.entityCode = entityCode;
    }
}
