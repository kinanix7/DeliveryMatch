package com.deliverymatch.dto.request;

import com.deliverymatch.entity.PackageType;
import lombok.Data;

@Data
public class CreateDeliveryRequest {
    private Long tripId;
    private String pickupLocation;
    private String deliveryLocation;
    private Double packageWidth;
    private Double packageHeight;
    private Double packageLength;
    private Double packageWeight;
    private PackageType packageType;
    private String description;
}

