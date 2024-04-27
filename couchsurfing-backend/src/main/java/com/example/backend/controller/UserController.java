package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;


    @GetMapping("/authenticated-user")
    public ResponseEntity<User> getAuthenticatedUser(Authentication connectedUser) {
        return ResponseEntity.ok((User) connectedUser.getPrincipal());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping("/hosts")
    public ResponseEntity<Page<User>> getHosts(@RequestParam @NotBlank String location,
                                               @RequestParam int page,
                                               @RequestParam int size) {

        return ResponseEntity.ok(userService.findHosts(location, page, size));
    }

    @GetMapping("/surfers")
    public ResponseEntity<Page<User>> getSurfers(@RequestParam @NotBlank String location,
                                                 @RequestParam int page,
                                                 @RequestParam int size) {

        return ResponseEntity.ok(userService.findSurfers(location, page, size));
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUserInfo(@RequestBody User user, Authentication connectedUser) {

        return ResponseEntity.ok(userService.updateUser(user, connectedUser));
    }

}
