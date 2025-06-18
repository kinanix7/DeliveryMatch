package com.deliverymatch.service;

import com.deliverymatch.dto.request.CreateDeliveryRequest;
import com.deliverymatch.dto.request.DeliveryRequestDto;
import com.deliverymatch.entity.DeliveryRequest;
import com.deliverymatch.entity.RequestStatus;
import com.deliverymatch.entity.Trip;
import com.deliverymatch.entity.User;
import com.deliverymatch.mapper.DeliveryRequestMapper;
import com.deliverymatch.repository.DeliveryRequestRepository;
import com.deliverymatch.repository.TripRepository;
import com.deliverymatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryRequestService {
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final DeliveryRequestMapper deliveryRequestMapper;

    public DeliveryRequestDto createRequest(CreateDeliveryRequest request, Authentication auth) {
        User sender = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        DeliveryRequest deliveryRequest = deliveryRequestMapper.toEntity(request);
        deliveryRequest.setSender(sender);
        deliveryRequest.setTrip(trip);
        deliveryRequest.setStatus(RequestStatus.PENDING);

        DeliveryRequest savedRequest = deliveryRequestRepository.save(deliveryRequest);
        return deliveryRequestMapper.toDto(savedRequest);
    }

    public List<DeliveryRequestDto> getMyRequests(Authentication auth) {
        User sender = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        List<DeliveryRequest> requests = deliveryRequestRepository.findBySender(sender);
        return deliveryRequestMapper.toDtoList(requests);
    }

    public List<DeliveryRequestDto> getRequestsForMyTrips(Authentication auth) {
        User driver = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        List<DeliveryRequest> requests = deliveryRequestRepository.findByDriver(driver);
        return deliveryRequestMapper.toDtoList(requests);
    }

    public DeliveryRequestDto updateRequestStatus(Long id, RequestStatus status, Authentication auth) {
        DeliveryRequest request = deliveryRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Vérifier que l'utilisateur est le conducteur du trajet
        if (!request.getTrip().getDriver().equals(user)) {
            throw new RuntimeException("Unauthorized to update this request");
        }

        request.setStatus(status);
        request.setUpdatedAt(LocalDateTime.now());

        DeliveryRequest savedRequest = deliveryRequestRepository.save(request);
        return deliveryRequestMapper.toDto(savedRequest);
    }

    public List<DeliveryRequestDto> getAllRequests() {
        return deliveryRequestMapper.toDtoList(deliveryRequestRepository.findAll());
    }
}

