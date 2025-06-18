package com.deliverymatch.service;

import com.deliverymatch.dto.trip.CreateTripRequest;
import com.deliverymatch.dto.trip.TripDto;
import com.deliverymatch.entity.Trip;
import com.deliverymatch.entity.TripStatus;
import com.deliverymatch.entity.User;
import com.deliverymatch.mapper.TripMapper;
import com.deliverymatch.repository.TripRepository;
import com.deliverymatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripMapper tripMapper;

    public TripDto createTrip(CreateTripRequest request, Authentication auth) {
        User driver = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Trip trip = tripMapper.toEntity(request);
        trip.setDriver(driver);
        trip.setStatus(TripStatus.ACTIVE);

        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toDto(savedTrip);
    }

    public List<TripDto> getAvailableTrips(String destination, LocalDateTime departureDate) {
        List<Trip> trips = tripRepository.findAvailableTrips(destination, departureDate);
        return tripMapper.toDtoList(trips);
    }

    public List<TripDto> getMyTrips(Authentication auth) {
        User driver = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        List<Trip> trips = tripRepository.findByDriverAndStatus(driver, TripStatus.ACTIVE);
        return tripMapper.toDtoList(trips);
    }

    public List<TripDto> getAllTrips() {
        return tripMapper.toDtoList(tripRepository.findAll());
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}

