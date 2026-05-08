package com.luanferro.reservation_api.application.dto.response;

import com.luanferro.reservation_api.domain.enums.StatusReservation;

import java.util.UUID;

public record ReservationSummaryResponse(UUID id, RestaurantTableResponse table, String date, StatusReservation status) {
}
