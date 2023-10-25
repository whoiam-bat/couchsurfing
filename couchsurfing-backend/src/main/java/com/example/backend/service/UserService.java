package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Trip;
import com.example.backend.model.User;
import com.example.backend.repository.TripRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TripRepository tripRepository;


    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Page<User> findUsersByLocation(String location, int page, int size) {
        return userRepository.findUsersByUserInfoLocation(location, PageRequest.of(page, size));
    }

    @Transactional
    public User updateUserInfo(User userToUpdate, String userId) {
        userToUpdate.setId(userId);

        return save(userToUpdate);
    }

    @Transactional
    public User save(User userToAdd) {
        return userRepository.save(userToAdd);
    }

    @Transactional
    public Trip addNewTrip(String userId, Trip trip) {
        User user = findUserById(userId);
        trip.setTravelerId(userId);

        tripRepository.save(trip);

        user.getTrips().add(trip);
        userRepository.save(user);

        return trip;
    }

    @Transactional
    public Trip updateTrip(String tripId, Trip trip) {
        trip.setId(tripId);

        return tripRepository.save(trip);
    }

}
