package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Review {

    private ObjectId id;

    private String senderId;

    private Boolean wouldRepeat;

    private ServiceType serviceType;

    private Double rating;

    private String reviewMessage;

    private Date timestamp;
}
