package com.deliverymatch.controller;

import com.deliverymatch.dto.request.CreateDeliveryRequest;
import com.deliverymatch.dto.request.DeliveryRequestDto;
import com.deliverymatch.entity.RequestStatus;
import com.deliverymatch.service.DeliveryRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DeliveryRequestController {
    private final DeliveryRequestService deliveryRequestService;

    @PostMapping("/create")
    public ResponseEntity<DeliveryRequestDto> createRequest(
            @RequestBody CreateDeliveryRequest request,
            Authentication auth) {
        return ResponseEntity.ok(deliveryRequestService.createRequest(request, auth));
    }

    @GetMapping("/my-requests")
    public ResponseEntity<List<DeliveryRequestDto>> getMyRequests(Authentication auth) {
        return ResponseEntity.ok(deliveryRequestService.getMyRequests(auth));
    }

    @GetMapping("/for-my-trips")
    public ResponseEntity<List<DeliveryRequestDto>> getRequestsForMyTrips(Authentication auth) {
        return ResponseEntity.ok(deliveryRequestService.getRequestsForMyTrips(auth));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeliveryRequestDto> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam RequestStatus status,
            Authentication auth) {
        return ResponseEntity.ok(deliveryRequestService.updateRequestStatus(id, status, auth));
    }
}

