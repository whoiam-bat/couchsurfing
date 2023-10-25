package com.example.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "trip")
@Data
@Builder
public class Trip {

    @Id
    private String id;

    private String travelerId;

    private LocalDateTime from;

    private LocalDateTime to;

    private Integer travelersAmount;

    private String location;

    private String message;

}
