package com.example.backend.repository;

import com.example.backend.model.Request;
import com.example.backend.model.enums.RequestStatus;
import com.example.backend.model.enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {

    Optional<Request> findByIdAndLocation(String requestId, String location);

    Page<Request> findRequestsByReceiverAndServiceTypeAndRequestStatusIn(
            String receiverId,
            ServiceType serviceType,
            List<RequestStatus> requestStatus,
            Pageable pageable
    );

    Page<Request> findRequestsBySenderAndServiceTypeAndRequestStatusIn(
            String senderId,
            ServiceType serviceType,
            List<RequestStatus> requestStatus,
            Pageable pageable
    );

}
