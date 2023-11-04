package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Page<User> findUsersByUserInfoLocation(String location, Pageable pageable);

    Page<User> findUsersByUserHomeIsAcceptingGuestsAndUserInfoLocation(Boolean isAcceptingGuests,
                                                                       String location,
                                                                       Pageable pageable);

    @Query("{ trips: { $elemMatch: { location: ?0 } } }")
    // TODO: select all surfers
    Page<User> findUsersByIncomingTrips(String location, LocalDate date, Pageable pageable);

}
