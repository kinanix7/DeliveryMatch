package com.deliverymatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;
    private String departureLocation;
    private String destination;
    private String intermediateStops;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Double maxWidth;
    private Double maxHeight;
    private Double maxLength;
    private Double maxWeight;
    @Enumerated(EnumType.STRING)
    private PackageType acceptedPackageType;
    private Integer availableCapacity;
    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.ACTIVE;
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<DeliveryRequest> deliveryRequests;
}

