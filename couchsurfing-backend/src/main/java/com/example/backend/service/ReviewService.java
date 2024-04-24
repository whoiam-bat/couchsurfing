package com.example.backend.service;


import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.exception.EntityUpdateException;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;

    private final ReviewRepository reviewRepository;

    private final ModelMapper modelMapper;


    public Review addReview(Review review) {
        User receiver = userService.findUserById(review.getReceiverId());
        User sender = userService.findUserById(review.getSenderId());

        double rating = reviewRepository.findReviewsByReceiverId(receiver.getId())
                .stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.);

        receiver.setRating(
                (double) Math.round(rating * 100) / 100
        );
        userService.updateUser(receiver, receiver.getId());

        review.setId(new ObjectId().toHexString());

        return reviewRepository.save(review);
    }

    public Review getReview(String reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public Page<Review> getIncomingReviews(String receiverId, int page, int size) {
        return reviewRepository.findReviewByReceiverId(receiverId, PageRequest.of(page, size));
    }

    public Page<Review> getOutgoingReviews(String senderId, int page, int size) {
        return reviewRepository.findReviewBySenderId(senderId, PageRequest.of(page, size));
    }

    public Boolean updateReview(Review reviewToUpdate, String reviewId) {
        try {
            Review review = getReview(reviewId);

            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(reviewToUpdate, review);

            reviewRepository.save(review);
        } catch (EntityNotFoundException e) {
            throw new EntityUpdateException("Something went wrong with entity update", e);
        }

        return true;
    }

    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
