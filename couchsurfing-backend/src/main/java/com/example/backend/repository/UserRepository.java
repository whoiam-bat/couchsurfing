package com.example.backend.repository;

import com.example.backend.model.User;
import com.example.backend.model.enums.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Page<User> findUsersByAuthoritiesContainingAndUserInfoLocationAndUserHomeIsAcceptingGuests(
            List<Authority> authority,
            String location,
            Boolean isAcceptingGuests,
            Pageable pageable
    );

    Page<User> findUsersByAuthoritiesContainingAndUserInfoLocation(
            List<Authority> authority,
            String location,
            Pageable pageable
    );
}
