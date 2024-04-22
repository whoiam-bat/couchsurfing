package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.exception.EntityUpdateException;
import com.example.backend.model.Request;
import com.example.backend.model.User;
import com.example.backend.model.enums.RequestStatus;
import com.example.backend.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final UserService userService;

    private final RequestRepository requestRepository;

    private final ModelMapper modelMapper;


    public Request accommodationRequest(Request request) {
        User to = userService.findUserById(request.getReceiver());
        User from = userService.findUserById(request.getSender());

        request.setId(new ObjectId().toHexString());
        request.setReceiver(to.getId());
        request.setSender(from.getId());
        request.setTimestamp(new Date());

        // TODO: send request message to HOST email

        return requestRepository.save(request);
    }

    public Request getRequest(String requestId, String location) {
        Optional<Request> optionalRequest = requestRepository.findByIdAndLocation(requestId, location);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Request getRequest(String requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Page<Request> getIncomingRequests(String receiverId, String location, int page, int size) {
        return requestRepository.findRequestsByReceiverAndLocation(receiverId, location, PageRequest.of(page, size));
    }

    public Page<Request> getOutgoingRequests(String senderId, int page, int size) {
        return requestRepository.findRequestsBySender(senderId, PageRequest.of(page, size));
    }

    public Request getOutgoingRequest(String senderId, String requestId) {
        Optional<Request> optionalRequest = requestRepository.findRequestByIdAndSender(requestId, senderId);

        return optionalRequest.orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public Boolean updateRequest(String requestId, Request requestToUpdate) {
        try {
            Request request = getRequest(requestId);

            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(requestToUpdate, request);

            requestRepository.save(request);
        } catch (EntityNotFoundException e) {
            throw new EntityUpdateException("Something went wrong with entity update", e);
        }

        return true;
    }

    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }
}
