package com.luanferro.reservation_api.application.dto.response;

import com.luanferro.reservation_api.domain.enums.StatusReservation;

import java.util.UUID;

public record ReservationResponse(UUID id, UserResponse user, RestaurantTableResponse table, String date, StatusReservation status) {
}
