package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.User;
import com.example.backend.repository.TripRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TripRepository tripRepository;


    public Page<User> findHosts(String location, int page, int size) {
        return userRepository.findUsersByUserHomeIsAcceptingGuestsAndUserInfoLocation(true,
                location,
                PageRequest.of(page, size));
    }

    public Page<User> findSurfers(String location, int page, int size) {
        return userRepository.findUsersByIncomingTrips(location,
                LocalDate.now(),
                PageRequest.of(page, size));
    }


    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Page<User> findUsersByLocation(String location, int page, int size) {
        return userRepository.findUsersByUserInfoLocation(location, PageRequest.of(page, size));
    }

    @Transactional
    public User updateUser(User userToUpdate, String userId) {
        userToUpdate.setId(userId);

        return save(userToUpdate);
    }

    @Transactional
    public User save(User userToAdd) {
        return userRepository.save(userToAdd);
    }

}
