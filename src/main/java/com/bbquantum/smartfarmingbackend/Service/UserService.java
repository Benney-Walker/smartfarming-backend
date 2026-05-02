package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Components.JwtUtil;
import com.bbquantum.smartfarmingbackend.Contants.UserRole;
import com.bbquantum.smartfarmingbackend.Contants.UserStatus;
import com.bbquantum.smartfarmingbackend.DTO.UI.AddNewUser;
import com.bbquantum.smartfarmingbackend.DTO.UI.GetUserDetails;
import com.bbquantum.smartfarmingbackend.DTO.UI.LoginRequest;
import com.bbquantum.smartfarmingbackend.Entity.UserRoles;
import com.bbquantum.smartfarmingbackend.Entity.Users;
import com.bbquantum.smartfarmingbackend.Repository.UserRolesRepo;
import com.bbquantum.smartfarmingbackend.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UserRolesRepo userRolesRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    public ResponseEntity<?> addNewUser(AddNewUser addNewUser) {
        String userName = addNewUser.getUserName();
        String emailAddress = addNewUser.getEmailAddress();
        String phoneNumber = addNewUser.getPhoneNumber();
        String role = addNewUser.getRole();

        if (usersRepo.findByUserName(userName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        if (usersRepo.findByEmailAddress(emailAddress).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email address already exists");
        }

        Users user = new Users();
        user.setUserName(userName);
        user.setEmailAddress(emailAddress);
        user.setPhoneNumber(phoneNumber);
        user.setDateOfRegistration(LocalDateTime.now());
        usersRepo.save(user);

        user.setUserRoles(addUserRoles(
                user,
                List.of(role)
        ));
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> initializeUserPassword(String emailAddress, String password) {
        Users user = usersRepo.findByEmailAddress(emailAddress).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String storedPassword = user.getPassword();
        if (storedPassword != null && !storedPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Password has already been set"
                    ));
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        usersRepo.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAuthValues(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = getUserData(request.getUsername());
        List<String> rolesList = user.getUserRoles().stream().map(
                role -> role.getUserRole().name()
        ).toList();

        String authToken = jwtUtil.generateJwtToken(user.getEmailAddress(), rolesList);

        return ResponseEntity.ok(Map.of(
                "username", user.getUserName(),
                "token", authToken,
                "role", rolesList.getFirst()
        ));
    }

    public ResponseEntity<?> checkUsers(String emailAddress) {
        Users user = usersRepo.findByEmailAddress(emailAddress).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "OLD_USER"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "NEW_USER"
        ));
    }

    public ResponseEntity<?> changeUserStatus(String email, String status) {
        Users user = usersRepo.findByEmailAddress(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        user.setUserStatus(UserStatus.valueOf(status));
        usersRepo.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> viewUserDetails(String email) {
        Users user = usersRepo.findByEmailAddress(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<String> roleList = user.getUserRoles().stream().map(
                role -> role.getUserRole().name()
        ).toList();

        GetUserDetails userDetails = new GetUserDetails(
                user.getUserName(),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                roleList,
                user.getUserStatus().name()
        );

        return ResponseEntity.ok(userDetails);
    }

    public ResponseEntity<?> loadAllUsers() {
        List<Users> users = usersRepo.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "No users found"
            ));
        }

        return ResponseEntity.ok(
                users.stream().map(user -> {
                    String userName = user.getUserName();
                    String emailAddress = user.getEmailAddress();
                    String phoneNumber = user.getPhoneNumber();
                    List<String> userRoles = user.getUserRoles().stream().map(
                            role -> role.getUserRole().name()
                    ).toList();
                    String userStatus = user.getUserStatus().name();

                    return new GetUserDetails(
                            userName,
                            emailAddress,
                            phoneNumber,
                            userRoles,
                            userStatus
                    );
                }).toList()
        );
    }

    public ResponseEntity<?> getTotalUsers() {
        return ResponseEntity.ok(Map.of(
                "totalUsers", usersRepo.count()
        ));
    }

    private Users getUserData(String emailAddress) throws UsernameNotFoundException {
        return usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private List<UserRoles> addUserRoles(Users user, List<String> roles) {
        List<UserRoles> userRoles = new ArrayList<>();
        for (String role : roles) {
            UserRoles userRole = new UserRoles(
                    user,
                    UserRole.valueOf(role)
            );
            userRolesRepo.save(userRole);
            userRoles.add(userRole);
        }

        return userRoles;
    }
}
