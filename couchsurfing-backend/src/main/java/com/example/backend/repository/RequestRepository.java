package com.example.backend.repository;

import com.example.backend.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {

    Optional<Request> findByIdAndLocation(String requestId, String location);

    Page<Request> findRequestsByReceiverAndLocation(String receiverId, String location, Pageable pageable);

    Page<Request> findRequestsBySender(String senderId, Pageable pageable);

    Optional<Request> findRequestByIdAndSender(String requestId, String senderId);

}
