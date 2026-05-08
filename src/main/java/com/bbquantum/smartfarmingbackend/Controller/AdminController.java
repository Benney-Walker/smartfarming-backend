package com.bbquantum.smartfarmingbackend.Controller;

import com.bbquantum.smartfarmingbackend.Components.Util;
import com.bbquantum.smartfarmingbackend.DTO.UI.AddNewField;
import com.bbquantum.smartfarmingbackend.Service.FieldService;
import com.bbquantum.smartfarmingbackend.Service.HybridEngineService;
import com.bbquantum.smartfarmingbackend.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final UserService userService;

    private final FieldService fieldService;

    private final HybridEngineService engineService;

    public AdminController(UserService userService, FieldService fieldService, HybridEngineService engineService) {
        this.userService = userService;
        this.fieldService = fieldService;
        this.engineService = engineService;
    }

    @PostMapping("/v1/add-new-field")
    public ResponseEntity<?> addNewField(@RequestBody AddNewField addNewField) {
        return fieldService.addNewField(addNewField);
    }
}
