package com.example.backend.repository;

import com.example.backend.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    Page<Review> findReviewByReceiverId(String receiverId, Pageable pageable);

    Page<Review> findReviewBySenderId(String senderId, Pageable pageable);

    List<Review> findReviewsByReceiverId(String receiverId);

}
