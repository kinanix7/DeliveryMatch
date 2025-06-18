package com.deliverymatch.dto.request;

import com.deliverymatch.entity.PackageType;
import com.deliverymatch.entity.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryRequestDto {
    private Long id;
    private String senderName;
    private String senderEmail;
    private Long tripId;
    private String pickupLocation;
    private String deliveryLocation;
    private Double packageWidth;
    private Double packageHeight;
    private Double packageLength;
    private Double packageWeight;
    private PackageType packageType;
    private String description;
    private RequestStatus status;
    private LocalDateTime createdAt;
}

