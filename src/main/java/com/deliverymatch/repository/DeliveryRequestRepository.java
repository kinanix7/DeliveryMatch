package com.deliverymatch.repository;

import com.deliverymatch.entity.DeliveryRequest;
import com.deliverymatch.entity.RequestStatus;
import com.deliverymatch.entity.Trip;
import com.deliverymatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Long> {
    List<DeliveryRequest> findBySender(User sender);
    List<DeliveryRequest> findByTrip(Trip trip);
    List<DeliveryRequest> findByStatus(RequestStatus status);

    @Query("SELECT dr FROM DeliveryRequest dr WHERE dr.trip.driver = :driver")
    List<DeliveryRequest> findByDriver(User driver);

    @Query("SELECT COUNT(dr) FROM DeliveryRequest dr WHERE dr.status = 'ACCEPTED'")
    long countAcceptedRequests();

    @Query("SELECT COUNT(dr) FROM DeliveryRequest dr")
    long countTotalRequests();
}

