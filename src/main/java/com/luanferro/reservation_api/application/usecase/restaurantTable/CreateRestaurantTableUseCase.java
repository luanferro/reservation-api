package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.application.dto.request.RestaurantTableRequest;
import com.luanferro.reservation_api.domain.model.RestaurantTable;

public interface CreateRestaurantTableUseCase {
    RestaurantTable create(RestaurantTableRequest request);
}
