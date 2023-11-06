package com.example.backend.controller;

import com.example.backend.model.Request;
import com.example.backend.service.RequestService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


    @PostMapping("/accommodation")
    public ResponseEntity<Request> sendAccommodationRequest(@RequestParam @NotBlank String hostId,
                                                           @RequestBody Request request) {

        /*
                User sends request
                receiver make request review
                after approval trip document is created
         */

        return ResponseEntity.ok(requestService.sentAccommodationRequest(hostId, request));
    }

}
