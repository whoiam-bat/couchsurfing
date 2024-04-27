package com.example.backend.model;

import com.example.backend.model.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
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

    @CreatedDate
    @JsonIgnore
    private LocalDateTime dateCreated;

    @Version
    @JsonIgnore
    private Integer version;
}
