package com.deliverymatch.controller;

import com.deliverymatch.dto.request.DeliveryRequestDto;
import com.deliverymatch.dto.statistics.StatisticsDto;
import com.deliverymatch.dto.trip.TripDto;
import com.deliverymatch.dto.user.UserDto;
import com.deliverymatch.service.DeliveryRequestService;
import com.deliverymatch.service.StatisticsService;
import com.deliverymatch.service.TripService;
import com.deliverymatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final UserService userService;
    private final TripService tripService;
    private final DeliveryRequestService deliveryRequestService;
    private final StatisticsService statisticsService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/{id}/toggle-status")
    public ResponseEntity<Void> toggleUserStatus(@PathVariable Long id) {
        userService.toggleUserStatus(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/verify")
    public ResponseEntity<Void> verifyUser(@PathVariable Long id) {
        userService.verifyUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/requests")
    public ResponseEntity<List<DeliveryRequestDto>> getAllRequests() {
        return ResponseEntity.ok(deliveryRequestService.getAllRequests());
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDto> getStatistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}

