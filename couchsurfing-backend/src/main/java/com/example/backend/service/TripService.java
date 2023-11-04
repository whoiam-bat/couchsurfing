package com.example.backend.service;


import com.example.backend.model.Trip;
import com.example.backend.model.User;
import com.example.backend.repository.TripRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    private final UserRepository userRepository;

    private final UserService userService;


    @Transactional
    public Trip addNewTrip(String userId, Trip trip) {
        User user = userService.findUserById(userId);
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
