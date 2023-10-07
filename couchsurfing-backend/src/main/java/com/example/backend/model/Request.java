package com.example.backend.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Request {

    private ObjectId id;

    @DocumentReference
    @ToString.Exclude
    private User senderId;

    @Min(value = 1, message = "value should be greater than 0")
    private Integer travelersAmount;

    @Min(value = 1, message = "value should be greater than 0")
    private Integer nightsToStay;

    private String message;

    private ServiceType serviceType;

    private Boolean isAccepted;

    private LocalDateTime timestamp;
}




