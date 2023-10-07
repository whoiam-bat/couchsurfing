package com.example.backend.repository;

import com.example.backend.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

    Page<Trip> findAllByLocation(String location, Pageable pageable);
}
