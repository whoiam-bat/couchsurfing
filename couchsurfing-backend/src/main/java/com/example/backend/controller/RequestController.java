package com.example.backend.controller;

import com.example.backend.model.Request;
import com.example.backend.service.RequestService;
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


    @PostMapping("/send-request")
    public ResponseEntity<Request> sendAccommodationRequest(@RequestBody Request request) {
        return ResponseEntity.ok(requestService.accommodationRequest(request));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequest(@PathVariable String requestId,
                                              @RequestParam(required = false) String location) {
        Request request = location == null ?
                requestService.getRequest(requestId) : requestService.getRequest(requestId, location);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/{receiverId}/incoming")
    public ResponseEntity<Page<Request>> getIncomingRequests(@PathVariable String receiverId,
                                                             @RequestParam String location,
                                                             @RequestParam int page,
                                                             @RequestParam int size) {

        return ResponseEntity.ok(requestService.getIncomingRequests(receiverId, location, page, size));
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

    @PatchMapping("/update/{requestId}")
    public ResponseEntity<Boolean> updateRequest(@PathVariable String requestId,
                                                 @RequestBody Request requestToUpdate) {

        return ResponseEntity.ok(requestService.updateRequest(requestId, requestToUpdate));
    }

    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable String requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.ok("Deleted successfully!");
    }

}
