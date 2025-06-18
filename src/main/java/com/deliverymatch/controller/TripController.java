package com.deliverymatch.controller;

import com.deliverymatch.dto.trip.CreateTripRequest;
import com.deliverymatch.dto.trip.TripDto;
import com.deliverymatch.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {
    private final TripService tripService;

    @PostMapping("/create")
    public ResponseEntity<TripDto> createTrip(
            @RequestBody CreateTripRequest request,
            Authentication auth) {
        return ResponseEntity.ok(tripService.createTrip(request, auth));
    }

    @GetMapping("/available")
    public ResponseEntity<List<TripDto>> getAvailableTrips(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate) {
        return ResponseEntity.ok(tripService.getAvailableTrips(destination, departureDate));
    }

    @GetMapping("/my-trips")
    public ResponseEntity<List<TripDto>> getMyTrips(Authentication auth) {
        return ResponseEntity.ok(tripService.getMyTrips(auth));
    }
}

