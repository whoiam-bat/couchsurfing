package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Request;
import com.example.backend.model.User;
import com.example.backend.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final UserService userService;

    private final RequestRepository requestRepository;


    public Request addNewTrip(String userId, Request trip) {
        User surfer = userService.findUserById(userId);

        surfer.getRequests().add(trip);
        return requestRepository.save(trip);
    }

    public Request sentAccommodationRequest(String hostId, Request request) {
        User to = userService.findUserById(hostId);
        User from = userService.findUserById(request.getSender());

        request.setId(new ObjectId().toHexString());
        request.setReceiver(hostId);

        from.getRequests().add(request);
        to.getRequests().add(request);

        userService.save(to);
        userService.save(from);

        // TODO: send request message to HOST email

        return requestRepository.save(request);
    }

    public Request getRequest(String requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Page<Request> getIncommingRequests(String receiverId, int page, int size) {

        return requestRepository.findRequestsByReceiver(receiverId, PageRequest.of(page, size));
    }

    public Page<Request> getOutgoingRequests(String senderId, int page, int size) {

        return requestRepository.findRequestsBySender(senderId, PageRequest.of(page, size));
    }
    /*
        TODO
        Add method to modify request by requestId and userId.
     */
}
