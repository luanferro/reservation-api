package com.luanferro.reservation_api.application.mapper;

import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.dto.response.ReservationResponse;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.model.User;
import jakarta.persistence.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "reserved", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "table", ignore = true)
    Reservation toEntity(ReservationRequest dto);

    ReservationResponse toResponse(Reservation reservation);
}
