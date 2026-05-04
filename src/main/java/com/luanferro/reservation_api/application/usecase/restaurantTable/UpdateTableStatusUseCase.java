package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;

import java.util.UUID;

public interface UpdateTableStatusUseCase {
    void updateStatus(UUID tableId, StatusTable status);
}
