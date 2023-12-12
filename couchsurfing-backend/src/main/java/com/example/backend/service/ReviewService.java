package com.example.backend.service;


import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;


    public void addReview(String userId, Review review) {

    }

}
