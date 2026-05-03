package com.luanferro.reservation_api.application.dto.response;

import java.util.UUID;

public record ReservationResponse(UUID id, UserResponse user, RestaurantTableResponse table, String date, boolean reserved) {
}
