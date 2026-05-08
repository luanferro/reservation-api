package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.model.RestaurantTable;

import java.util.List;
import java.util.UUID;

public interface FindRestaurantTableUseCase {
    List<RestaurantTable> findAll(String status);
    RestaurantTable findById(UUID id);
}
