package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);

    Page<User> findUsersByUserInfoLocation(String location, Pageable pageable);

    Page<User> findUsersByUserHomeIsAcceptingGuestsAndUserInfoLocation(Boolean isAcceptingGuests,
                                                                       String location,
                                                                       Pageable pageable);

    @Query("{ requests: { $elemMatch: { isAccepted: ?0, location: ?1 } } }")
    Page<User> findUsersByIncomingTrips(Boolean isAccepted, String location, Pageable pageable);

}
