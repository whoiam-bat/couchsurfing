package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.exception.EntityUpdateException;
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

        trip.setId(new ObjectId().toHexString());
        trip.setSender(surfer.getId());

        return requestRepository.save(trip);
    }

    public Request addNewTrip(String userId, String hostId, Request request) {
        User to = userService.findUserById(hostId);
        User from = userService.findUserById(userId);

        request.setId(new ObjectId().toHexString());
        request.setReceiver(to.getId());
        request.setSender(from.getId());

        // TODO: send request message to HOST email

        return requestRepository.save(request);
    }

    public Request getRequest(String requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Page<Request> getIncomingRequests(String receiverId, int page, int size) {

        return requestRepository.findRequestsByReceiver(receiverId, PageRequest.of(page, size));
    }

    public Page<Request> getOutgoingRequests(String senderId, int page, int size) {

        return requestRepository.findRequestsBySender(senderId, PageRequest.of(page, size));
    }

    public Request getOutgoingRequest(String senderId, String requestId) {
        Optional<Request> optionalRequest = requestRepository.findRequestByIdAndSender(requestId, senderId);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Boolean updateRequest(String requestId, String userId, Request requestToUpdate) {
        try {
            User user = userService.findUserById(userId);
            Request request = getOutgoingRequest(userId, requestId);

            requestToUpdate.setId(requestId);
            requestToUpdate.setSender(user.getId());
            requestToUpdate.setReceiver(request.getReceiver());

            requestRepository.save(requestToUpdate);
        } catch (EntityNotFoundException e) {
            throw new EntityUpdateException("Something went wrong with entity update", e);
        }

        return true;
    }

    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }
}
