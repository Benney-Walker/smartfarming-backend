package com.bbquantum.smartfarmingbackend.Controller;

import com.bbquantum.smartfarmingbackend.DTO.UI.AddNewUser;
import com.bbquantum.smartfarmingbackend.DTO.UI.LoginRequest;
import com.bbquantum.smartfarmingbackend.DTO.WebSocket.InitializeUser;
import com.bbquantum.smartfarmingbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userService.getAuthValues(loginRequest);
    }

    @GetMapping("/check-user")
    public ResponseEntity<?> checkUsers(@RequestParam String emailAddress) {
        return userService.checkUsers(emailAddress);
    }

    @PutMapping("/set-new-password")
    public ResponseEntity<?> initPassword(@RequestBody InitializeUser initializeUser) {
        return userService.initializeUserPassword(
                initializeUser.getEmailAddress(),
                initializeUser.getInitialPassword());
    }

    @PostMapping("/v1/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody AddNewUser addNewUser) {
        return userService.addNewUser(addNewUser);
    }
}
