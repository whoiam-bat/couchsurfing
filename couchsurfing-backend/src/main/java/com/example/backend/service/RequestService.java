package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Request;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final UserRepository userRepository;


    public void sentAccommodationRequest(String hostId, Request request) {
        Optional<User> optionalHost = userRepository.findById(hostId);
        Optional<User> sender = userRepository.findById(request.getFrom());

        if (optionalHost.isPresent() && sender.isPresent()) {
            request.setId(new ObjectId());
            request.setTo(hostId);

            User from = sender.get();
            User to = optionalHost.get();

            from.getRequests().add(request);
            to.getRequests().add(request);

            userRepository.save(to);
            userRepository.save(from);

            // TODO: send request message to HOST email
        } else
            throw new EntityNotFoundException("User not found");
    }


    /**
     * TODO
     *  Add getIncomingRequests method
     *  Add getOutgoingRequest method
     */

}
