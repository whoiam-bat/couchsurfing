package com.example.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "trip")
@Data
@Builder
public class Trip {

    @Id
    private String id;

    private String travelerId;

    private Date from;

    private Date to;

    private Integer travelersAmount;

    private String location;

    private String message;

}
