package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Components.Util;
import com.bbquantum.smartfarmingbackend.DTO.UI.AddNewField;
import com.bbquantum.smartfarmingbackend.DTO.UI.LoadAllFields;
import com.bbquantum.smartfarmingbackend.Entity.Fields;
import com.bbquantum.smartfarmingbackend.Entity.Users;
import com.bbquantum.smartfarmingbackend.Repository.FieldsRepo;
import com.bbquantum.smartfarmingbackend.Repository.UsersRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {

    private final FieldsRepo fieldsRepo;

    private final UsersRepo usersRepo;

    private final Util util;

    public FieldService(FieldsRepo fieldsRepo, UsersRepo usersRepo, Util util) {
        this.fieldsRepo = fieldsRepo;
        this.usersRepo = usersRepo;
        this.util = util;
    }

    public ResponseEntity<?> addNewField(AddNewField addNewField) {
        String fieldName = addNewField.getFieldName();
        String fieldLocation = addNewField.getFieldLocation();
        int userId = addNewField.getUserId();

        Users user = usersRepo.findByUserId(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "Selected User not found."
            ));
        }

        if (fieldsRepo.existsByFieldName(fieldName)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "message", "Field already exists."
            ));
        }

        //Save new field
        Fields field = new Fields(
                util.generateEntityId("FIELD"),
                fieldName,
                fieldLocation,
                LocalDateTime.now(),
                user
        );
        fieldsRepo.save(field);

        //Add field to client fields
        List<Fields> userFields = user.getFields();
        if (userFields == null) {
            userFields = new ArrayList<>();
        }
        userFields.add(field);
        usersRepo.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Field save successfully"
        ));
    }

    public ResponseEntity<?> loadAllFields() {
        List<Fields> fields = fieldsRepo.findAll();
        if (fields.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "No fields found"
            ));
        }

        return ResponseEntity.ok(fields.stream().map(
                field -> {
                    String fieldId = field.getFieldId();
                    String fieldName = field.getFieldName();
                    String fieldLocation = field.getFieldLocation();
                    String fieldStatus = field.getFieldStatus().name();

                    return new LoadAllFields(
                            fieldId,
                            fieldName,
                            fieldLocation,
                            fieldStatus
                    );
                }
        ).toList());
    }

    public ResponseEntity<?> getFieldsCount() {
        return ResponseEntity.ok(Map.of(
                "message", fieldsRepo.count()
        ));
    }
}
