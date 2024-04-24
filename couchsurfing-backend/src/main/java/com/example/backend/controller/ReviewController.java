package com.example.backend.controller;

import com.example.backend.model.Review;
import com.example.backend.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable String reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @GetMapping("/incoming")
    public ResponseEntity<Page<Review>> getIncomingReviews(@RequestParam int page,
                                                           @RequestParam int size,
                                                           Authentication connectedUser) {
        return ResponseEntity.ok(reviewService.getIncomingReviews(connectedUser, page, size));
    }

    @GetMapping("/outgoing")
    public ResponseEntity<Page<Review>> getOutgoingReviews(@RequestParam int page,
                                                           @RequestParam int size,
                                                           Authentication connectedUser) {
        return ResponseEntity.ok(reviewService.getOutgoingReviews(connectedUser, page, size));
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
