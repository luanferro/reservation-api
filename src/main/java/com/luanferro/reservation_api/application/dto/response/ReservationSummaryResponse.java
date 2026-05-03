package com.luanferro.reservation_api.application.dto.response;

import java.util.UUID;

public record ReservationSummaryResponse(UUID id, RestaurantTableResponse table, String date, boolean reserved) {
}
