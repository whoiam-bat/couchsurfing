package com.example.backend.controller;

import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.service.ReviewService;
import com.example.backend.service.UserService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ReviewService reviewService;


    @GetMapping("/hosts")
    public ResponseEntity<Page<User>> getHosts(@RequestParam @NotBlank String location,
                                               @RequestParam @NotBlank int page,
                                               @RequestParam @NotBlank int size) {

        return ResponseEntity.ok(userService.findHosts(location, page, size));
    }

    @GetMapping("/surfers")
    public ResponseEntity<Page<User>> getSurfers(@RequestParam @NotBlank String location,
                                                 @RequestParam @NotBlank int page,
                                                 @RequestParam @NotBlank int size) {

        return ResponseEntity.ok(userService.findSurfers(location, page, size));
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User userToAdd) {

        return ResponseEntity.ok(userService.save(userToAdd));
    }

    @PatchMapping("/{_userId}/update")
    public ResponseEntity<User> updateUserInfo(@PathVariable(name = "_userId") String userId,
                                               @RequestBody User user) {

        return ResponseEntity.ok(userService.updateUser(user, userId));
    }

    // TODO: Refactor
    /*@PostMapping("/{_userId}/new-trip")
    public ResponseEntity<Trip> addNewTrip(@PathVariable(name = "_userId") String userId,
                                             @RequestBody Trip trip) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tripService.addNewTrip(userId, trip));
    }

    @PatchMapping("/{_userId}/update/{_tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable(name = "_userId") String userId,
                                           @PathVariable(name = "_tripId") String tripId,
                                           @RequestBody Trip trip) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tripService.updateTrip(tripId, trip));
    }*/
}
