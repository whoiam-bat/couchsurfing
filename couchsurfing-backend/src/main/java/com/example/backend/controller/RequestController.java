package com.example.backend.controller;

import com.example.backend.model.Request;
import com.example.backend.service.RequestService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


    @PostMapping()
    public ResponseEntity<Request> addNewTrip(@RequestBody Request request) {
        // TODO: update this method to get user id dynamically from DB
        return ResponseEntity.ok(requestService.addNewTrip("", request));
    }

    @PostMapping("/accommodation")
    public ResponseEntity<Request> sendAccommodationRequest(@RequestParam @NotBlank String hostId,
                                                            @RequestBody Request request) {

        /*
                User sends request
                receiver make request review
                after approval trip document is created
         */
        // TODO: update this method to get user id dynamically from DB
        return ResponseEntity.ok(requestService.addNewTrip("", hostId, request));
    }

    @GetMapping("/get/{requestId}")
    public ResponseEntity<Request> getRequest(@PathVariable String requestId) {
        return ResponseEntity.ok(requestService.getRequest(requestId));
    }

    @GetMapping("/{receiverId}/incoming")
    public ResponseEntity<Page<Request>> getIncomingRequests(@PathVariable String receiverId,
                                                             @RequestParam int page,
                                                             @RequestParam int size) {

        return ResponseEntity.ok(requestService.getIncomingRequests(receiverId, page, size));
    }

    @GetMapping("/{senderId}/outgoing")
    public ResponseEntity<Page<Request>> getOutgoingRequests(@PathVariable String senderId,
                                                             @RequestParam int page,
                                                             @RequestParam int size) {

        return ResponseEntity.ok(requestService.getOutgoingRequests(senderId, page, size));
    }

    @GetMapping("/{senderId}/outgoing/{requestId}")
    public ResponseEntity<Request> getOutgoingRequest(@PathVariable String senderId,
                                                      @PathVariable String requestId) {

        return ResponseEntity.ok(requestService.getOutgoingRequest(senderId, requestId));
    }

}
