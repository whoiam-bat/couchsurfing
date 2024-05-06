package com.example.backend.controller;

import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.model.enums.ServiceType;
import com.example.backend.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews")
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @GetMapping("/by-reviewId")
    public ResponseEntity<Review> getReviewById(@RequestParam String reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @GetMapping("/by-requestId")
    public ResponseEntity<Review> getReviewByRequestId(@RequestParam String requestId,
                                                       @RequestParam ServiceType serviceType) {
        return ResponseEntity.ok(reviewService.getReviewByRequestIdAndServiceType(requestId, serviceType));
    }

    @GetMapping("/incoming")
    public ResponseEntity<Page<Review>> getIncomingReviews(@RequestParam int page,
                                                           @RequestParam int size,
                                                           @RequestParam(required = false) String userId,
                                                           @RequestParam ServiceType serviceType,
                                                           Authentication connectedUser) {
        Page<Review> reviewsPage;
        if (userId == null) {
            User receiver = (User) connectedUser.getPrincipal();
            reviewsPage = reviewService.getIncomingReviews(receiver.getId(), serviceType, page, size);
        } else {
            reviewsPage = reviewService.getIncomingReviews(userId, serviceType, page, size);
        }

        return ResponseEntity.ok(reviewsPage);
    }

    @GetMapping("/all-incoming")
    public ResponseEntity<List<Review>> getAllIncomingReviews(@RequestParam(required = false) String userId,
                                                              @RequestParam ServiceType serviceType,
                                                              Authentication connectedUser) {
        List<Review> reviews;
        if (userId == null) {
            User receiver = (User) connectedUser.getPrincipal();
            reviews = reviewService.getAllIncomingReviews(receiver.getId(), serviceType);
        } else {
            reviews = reviewService.getAllIncomingReviews(userId, serviceType);
        }
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/outgoing")
    public ResponseEntity<Page<Review>> getOutgoingReviews(@RequestParam int page,
                                                           @RequestParam int size,
                                                           @RequestParam ServiceType serviceType,
                                                           Authentication connectedUser) {
        return ResponseEntity.ok(reviewService.getOutgoingReviews(connectedUser, serviceType, page, size));
    }

    @PatchMapping("/update/{reviewId}")
    public ResponseEntity<Boolean> updateReview(@PathVariable String reviewId,
                                                @RequestBody Review reviewToUpdate) {
        return ResponseEntity.ok(reviewService.updateReview(reviewToUpdate, reviewId));
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
