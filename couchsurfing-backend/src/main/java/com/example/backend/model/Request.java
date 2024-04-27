package com.example.backend.model;

import com.example.backend.model.enums.RequestStatus;
import com.example.backend.model.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "requests")
@Data
@AllArgsConstructor
@Builder
public class Request {

    @Id
    private String id;

    private String sender;

    private String receiver;

    @Min(value = 1, message = "value should be greater than 0")
    private Integer travelersAmount;

    private Date from;

    private Date to;

    private String message;

    private ServiceType serviceType;

    private String location;

    private RequestStatus requestStatus;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime dateCreated;

    @Version
    @JsonIgnore
    private Integer version;
}




