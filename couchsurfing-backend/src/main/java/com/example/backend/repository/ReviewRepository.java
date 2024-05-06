package com.example.backend.repository;

import com.example.backend.model.Review;
import com.example.backend.model.enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    Page<Review> findReviewByReceiverIdAndServiceType(String receiverId, ServiceType serviceType, Pageable pageable);

    Page<Review> findReviewBySenderIdAndServiceType(String senderId, ServiceType serviceType, Pageable pageable);

    List<Review> findReviewsByReceiverIdAndServiceType(String receiverId, ServiceType serviceType);

    List<Review> findReviewsByReceiverId(String receiverId);

    Optional<Review> findReviewByRequestIdAndServiceType(String requestId, ServiceType serviceType);

}
