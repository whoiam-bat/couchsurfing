package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "review")
@Data
@AllArgsConstructor
@Builder
public class Review {

    @Id
    private String id;

    private String senderId;

    private String receiverId;

    private Boolean wouldRepeat;

    private ServiceType serviceType;

    private Double rating;

    private String reviewMessage;

    private Date timestamp;
}
