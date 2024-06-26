package com.example.backend.controller;

import com.example.backend.model.Request;
import com.example.backend.model.enums.RequestStatus;
import com.example.backend.service.RequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
@Tag(name = "Requests")
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

    @GetMapping("/incoming")
    public ResponseEntity<Page<Request>> getIncomingRequests(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam List<RequestStatus> requestStatusList,
                                                             Authentication connectedUser) {
        return ResponseEntity.ok(
                requestService.getIncomingRequests(connectedUser, requestStatusList, page, size)
        );
    }

    @GetMapping("/outgoing")
    public ResponseEntity<Page<Request>> getOutgoingRequests(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam List<RequestStatus> requestStatusList,
                                                             Authentication connectedUser) {
        return ResponseEntity.ok(
                requestService.getOutgoingRequests(connectedUser, requestStatusList, page, size)
        );
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
