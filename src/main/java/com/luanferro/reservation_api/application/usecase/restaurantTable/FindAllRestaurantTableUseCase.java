package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.model.RestaurantTable;

import java.util.List;

public interface FindAllRestaurantTableUseCase {
    List<RestaurantTable> findAll(String status);
}
