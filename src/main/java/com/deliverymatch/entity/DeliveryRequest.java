
package com.deliverymatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    private String pickupLocation;
    private String deliveryLocation;
    private Double packageWidth;
    private Double packageHeight;
    private Double packageLength;
    private Double packageWeight;
    @Enumerated(EnumType.STRING)
    private PackageType packageType;
    private String description;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}

