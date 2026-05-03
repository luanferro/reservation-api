package com.luanferro.reservation_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequest(UUID table, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date) {
}
