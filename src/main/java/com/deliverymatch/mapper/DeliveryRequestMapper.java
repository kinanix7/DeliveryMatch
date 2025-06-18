package com.deliverymatch.mapper;

import com.deliverymatch.dto.request.CreateDeliveryRequest;
import com.deliverymatch.dto.request.DeliveryRequestDto;
import com.deliverymatch.entity.DeliveryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryRequestMapper {
    @Mapping(source = "sender.firstName", target = "senderName")
    @Mapping(source = "sender.email", target = "senderEmail")
    @Mapping(source = "trip.id", target = "tripId")
    DeliveryRequestDto toDto(DeliveryRequest deliveryRequest);

    List<DeliveryRequestDto> toDtoList(List<DeliveryRequest> deliveryRequests);

    DeliveryRequest toEntity(CreateDeliveryRequest request);
}

