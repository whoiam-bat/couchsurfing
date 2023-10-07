package com.example.backend.model;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UserHome {

    @Min(value = 0, message = "Value should be positive")
    private Integer availableHostNights;

    @Min(value = 0, message = "Value should be positive")
    private Integer maxGuests;

    private Gender preferredGender;

    private Boolean kidFriendly;

    private Boolean petFriendly;

    private Boolean wheelchairAllowed;

    private Boolean smokingAllowed;

    private Boolean iHaveKids;

    private Boolean iHavePets;

    private Boolean iAmSmoker;

    private String otherInfo;
}