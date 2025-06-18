package com.deliverymatch.dto.statistics;

import lombok.Data;

@Data
public class StatisticsDto {
    private long totalUsers;
    private long activeUsers;
    private long totalTrips;
    private long activeTrips;
    private long totalRequests;
    private long acceptedRequests;
    private double acceptanceRate;
}

