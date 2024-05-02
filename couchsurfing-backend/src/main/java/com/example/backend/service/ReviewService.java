package com.example.backend.service;


import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.exception.EntityUpdateException;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.model.enums.ServiceType;
import com.example.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;

    private final ReviewRepository reviewRepository;

    private final ModelMapper modelMapper;


    public Review addReview(Review review) {
        User receiver = userService.findUserById(review.getReceiverId());

        review.setId(new ObjectId().toHexString());
        Review savedReview = reviewRepository.save(review);
        updateReceiverRating(receiver);

        return savedReview;
    }

    public Review getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public Review getReviewByRequestId(String requestId) {
        return reviewRepository.findReviewByRequestId(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public Page<Review> getIncomingReviews(Authentication authentication, ServiceType serviceType, int page, int size) {
        User receiver = (User) authentication.getPrincipal();

        return reviewRepository.findReviewByReceiverIdAndServiceType(receiver.getId(), serviceType, PageRequest.of(page, size));
    }

    public List<Review> getAllIncomingReviews(Authentication authentication, ServiceType serviceType) {
        User receiver = (User) authentication.getPrincipal();

        return reviewRepository.findReviewsByReceiverIdAndServiceType(receiver.getId(), serviceType);
    }

    public Page<Review> getOutgoingReviews(Authentication authentication, ServiceType serviceType, int page, int size) {
        User sender = (User) authentication.getPrincipal();

        return reviewRepository.findReviewBySenderIdAndServiceType(sender.getId(), serviceType, PageRequest.of(page, size));
    }

    public Boolean updateReview(Review reviewToUpdate, String reviewId) {
        try {
            User receiver = userService.findUserById(reviewToUpdate.getReceiverId());
            Review review = getReviewById(reviewId);

            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(reviewToUpdate, review);

            reviewRepository.save(review);
            updateReceiverRating(receiver);
        } catch (EntityNotFoundException e) {
            throw new EntityUpdateException("Something went wrong with entity update", e);
        }

        return true;
    }

    public void deleteReview(String reviewId) {
        Review review = getReviewById(reviewId);
        User receiver = userService.findUserById(review.getReceiverId());

        reviewRepository.deleteById(reviewId);
        updateReceiverRating(receiver);
    }

    private void updateReceiverRating(User receiver) {
        double rating = reviewRepository.findReviewsByReceiverId(receiver.getId())
                .stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.);
        receiver.setRating(
                (double) Math.round(rating * 10.) / 10.
        );
        userService.updateUser(receiver);
    }

}
