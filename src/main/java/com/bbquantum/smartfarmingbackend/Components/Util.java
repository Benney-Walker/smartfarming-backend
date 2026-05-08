package com.bbquantum.smartfarmingbackend.Components;

import com.bbquantum.smartfarmingbackend.Entity.EntityIdGeneration;
import com.bbquantum.smartfarmingbackend.Repository.EntityIdGenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Component
public class Util {

    @Autowired
    private EntityIdGenRepo entityIdGenRepo;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public String generateEntityId(String entityName) {
        String prefix;
        int code;

        switch (entityName) {
            case "FIELD" -> {
                prefix = "FD";
                code = 100000;
                return getId(entityName, prefix, code);
            }
            case "PREPARED_DATA" -> {
                prefix = "DT";
                code = 200000;
                return getId(entityName, prefix, code);
            }
            case "IRRIGATION_ACTION" -> {
                prefix = "AC";
                code = 300000;
                return getId(entityName, prefix, code);
            }
        }

        return "";
    }

    private String getId(String entityName, String prefix, int code) {
        EntityIdGeneration entity = entityIdGenRepo.findByEntityName(entityName)
                .orElse(new EntityIdGeneration(entityName, code));

        int oldCode = entity.getEntityCode();
        int newCode = oldCode + 1;

        entity.setEntityCode(newCode);
        entityIdGenRepo.save(entity);

        return prefix + newCode;
    }
}
