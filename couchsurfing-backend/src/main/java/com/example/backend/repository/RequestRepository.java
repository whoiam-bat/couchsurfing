package com.example.backend.repository;

import com.example.backend.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {

    Page<Request> findRequestsByReceiver(String receiverId, Pageable pageable);

    Page<Request> findRequestsBySender(String senderId, Pageable pageable);

}
