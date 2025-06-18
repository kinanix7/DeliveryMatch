package com.deliverymatch.dto.trip;

import com.deliverymatch.entity.PackageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTripRequest {
    private String departureLocation;
    private String destination;
    private String intermediateStops;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Double maxWidth;
    private Double maxHeight;
    private Double maxLength;
    private Double maxWeight;
    private PackageType acceptedPackageType;
    private Integer availableCapacity;
}

