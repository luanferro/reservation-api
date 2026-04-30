package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;

import java.util.List;

public interface RestaurantTableRepository {
    List<RestaurantTable> findAll();
    RestaurantTable save(RestaurantTable table);
    List<RestaurantTable> findByStatus(StatusTable status);
}
