package com.deliverymatch.repository;

import com.deliverymatch.entity.Trip;
import com.deliverymatch.entity.TripStatus;
import com.deliverymatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverAndStatus(User driver, TripStatus status);
    List<Trip> findByStatus(TripStatus status);

    @Query("SELECT t FROM Trip t WHERE t.status = 'ACTIVE' AND " +
            "(:destination IS NULL OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :destination, '%'))) AND " +
            "(:departureDate IS NULL OR t.departureDate >= :departureDate)")
    List<Trip> findAvailableTrips(@Param("destination") String destination,
                                  @Param("departureDate") LocalDateTime departureDate);

    @Query("SELECT COUNT(t) FROM Trip t WHERE t.status = 'ACTIVE'")
    long countActiveTrips();
}

