package com.example.backend.service;

import com.example.backend.exception.DateMismatchException;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.exception.EntityUpdateException;
import com.example.backend.model.Request;
import com.example.backend.model.User;
import com.example.backend.model.enums.RequestStatus;
import com.example.backend.model.enums.ServiceType;
import com.example.backend.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    private final ModelMapper modelMapper;


    public Request accommodationRequest(Request request) {
        if (request.getTo().before(request.getFrom()))
            throw new DateMismatchException("Invalid dates order! The check-out date must be later than the check-in date.");

        request.setId(new ObjectId().toHexString());

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

    public Page<Request> getIncomingRequests(Authentication authentication,
                                             List<RequestStatus> requestStatusList,
                                             int page, int size) {
        User receiver = (User) authentication.getPrincipal();

        return requestRepository.findRequestsByReceiverAndServiceTypeAndRequestStatusIn(
                receiver.getId(),
                ServiceType.ACCOMMODATION_REQUEST,
                requestStatusList,
                PageRequest.of(page, size)
        );
    }

    public Page<Request> getOutgoingRequests(Authentication authentication,
                                             List<RequestStatus> requestStatusList,
                                             int page, int size) {
        User sender = (User) authentication.getPrincipal();

        return requestRepository.findRequestsBySenderAndServiceTypeAndRequestStatusIn(
                sender.getId(),
                ServiceType.ACCOMMODATION_REQUEST,
                requestStatusList,
                PageRequest.of(page, size)
        );
    }

    public Boolean updateRequest(String requestId, Request requestToUpdate) {
        if (requestToUpdate.getRequestStatus().equals(RequestStatus.CANCELED) ||
                requestToUpdate.getRequestStatus().equals(RequestStatus.DECLINED)) {
            deleteRequest(requestId);
            return true;
        }

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
