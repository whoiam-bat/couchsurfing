package com.example.backend.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "trip")
@Data
public class Trip {

    @Id
    private String id;

    @DocumentReference
    @ToString.Exclude
    private User travelerId;

    private LocalDateTime from;

    private LocalDateTime to;

    private Integer travelersAmount;

    private String location;

    private String message;

    public Trip(User travelerId, LocalDateTime from, LocalDateTime to,
                Integer travelersAmount, String location, String message) {

        this.travelerId = travelerId;
        this.from = from;
        this.to = to;
        this.travelersAmount = travelersAmount;
        this.location = location;
        this.message = message;
    }
}
