package com.bbquantum.smartfarmingbackend.Controller;

import com.bbquantum.smartfarmingbackend.DTO.UI.AddNewField;
import com.bbquantum.smartfarmingbackend.DTO.UI.UpdateUserStatus;
import com.bbquantum.smartfarmingbackend.Service.FieldService;
import com.bbquantum.smartfarmingbackend.Service.HybridEngineService;
import com.bbquantum.smartfarmingbackend.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    private final FieldService fieldService;

    private final HybridEngineService engineService;

    public AdminController(UserService userService, FieldService fieldService, HybridEngineService engineService) {
        this.userService = userService;
        this.fieldService = fieldService;
        this.engineService = engineService;
    }

    //Users endpoint
    @GetMapping("/v1/total-users")
    public ResponseEntity<?> getUsersCount() {
        return userService.getTotalUsers();
    }

    @GetMapping("/v1/load-users")
    public ResponseEntity<?> loadAllUsers() {
        return userService.loadAllUsers();
    }

    @PutMapping("/v1/user-status")
    public ResponseEntity<?> updateUserStatus(@RequestBody UpdateUserStatus userStatus) {
        String email = userStatus.getEmailAddress();
        String status = userStatus.getStatus();

        return userService.changeUserStatus(email, status);
    }

    @GetMapping("/v1/user-details/{email}")
    public ResponseEntity<?> getUserDetails(@PathVariable String email) {
        return userService.viewUserDetails(email);
    }

    // Field endpoints
    @PostMapping("/v1/add-new-field")
    public ResponseEntity<?> addNewField(@RequestBody AddNewField addNewField) {
        return fieldService.addNewField(addNewField);
    }

    @GetMapping("/v1/total-fields")
    public ResponseEntity<?> getFieldsCount() {
        return fieldService.getFieldsCount();
    }

    @GetMapping("/v1/load-all-fields")
    public ResponseEntity<?> loadAllFields() {
        return fieldService.loadAllFields();
    }

    //Hybrid model endpoint
    @GetMapping("/v1/model-status")
    public ResponseEntity<?> getModelStatus() {
        String response = engineService.checkModelStatus();

        return ResponseEntity.ok(Map.of(
                "status", response
        ));
    }
}
