package com.deliverymatch.service;

import com.deliverymatch.dto.statistics.StatisticsDto;
import com.deliverymatch.repository.DeliveryRequestRepository;
import com.deliverymatch.repository.TripRepository;
import com.deliverymatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final DeliveryRequestRepository deliveryRequestRepository;

    public StatisticsDto getStatistics() {
        StatisticsDto stats = new StatisticsDto();
        stats.setTotalUsers(userRepository.count());
        stats.setActiveUsers(userRepository.countActiveUsers());
        stats.setTotalTrips(tripRepository.count());
        stats.setActiveTrips(tripRepository.countActiveTrips());
        stats.setTotalRequests(deliveryRequestRepository.countTotalRequests());
        stats.setAcceptedRequests(deliveryRequestRepository.countAcceptedRequests());

        // Calcul du taux d'acceptation
        long totalRequests = stats.getTotalRequests();
        if (totalRequests > 0) {
            double acceptanceRate = (double) stats.getAcceptedRequests() / totalRequests * 100;
            stats.setAcceptanceRate(acceptanceRate);
        } else {
            stats.setAcceptanceRate(0.0);
        }

        return stats;
    }
}

