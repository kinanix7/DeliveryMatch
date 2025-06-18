package com.deliverymatch.mapper;

import com.deliverymatch.dto.trip.CreateTripRequest;
import com.deliverymatch.dto.trip.TripDto;
import com.deliverymatch.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {
    @Mapping(source = "driver.firstName", target = "driverName")
    TripDto toDto(Trip trip);

    List<TripDto> toDtoList(List<Trip> trips);

    Trip toEntity(CreateTripRequest request);
}

