package com.example.jwt.admin;

import com.example.jwt.user.User;
import com.example.jwt.user.UserRoleRequest;
import com.example.jwt.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable Integer userId, @RequestBody UserRoleRequest request) {
        User user = userService.getUserById(userId);
        user.setRole(request.getRole());
        userService.saveUser(user);
        return ResponseEntity.ok("User role changed successfully");
    }


    @PutMapping("/users/{userId}/account")
//    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> toggleAccountStatus(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        boolean isActive = user.isActive();
        user.setActive(!isActive);
        userService.saveUser(user);
        String message = isActive ? "User account deactivated" : "User account activated";
        return ResponseEntity.ok(message);
    }
}

