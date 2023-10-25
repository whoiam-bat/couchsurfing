package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Review {

    private ObjectId id;

    @DocumentReference
    @ToString.Exclude
    private User senderId;

    private Boolean wouldRepeat;

    private ServiceType serviceType;

    private Double rating;

    private String reviewMessage;

    private LocalDateTime timestamp;
}
