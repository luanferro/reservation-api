package com.luanferro.reservation_api.application.dto.response;

import com.luanferro.reservation_api.domain.enums.StatusTable;

import java.util.UUID;

public record RestaurantTableResponse(UUID id, String nome, Integer capacidade, StatusTable status) {
}
