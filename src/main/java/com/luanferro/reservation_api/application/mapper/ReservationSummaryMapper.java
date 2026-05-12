package com.luanferro.reservation_api.application.mapper;

import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.dto.response.ReservationResponse;
import com.luanferro.reservation_api.application.dto.response.ReservationSummaryResponse;
import com.luanferro.reservation_api.domain.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationSummaryMapper {

    ReservationSummaryResponse toResponse(Reservation reservation);
}
