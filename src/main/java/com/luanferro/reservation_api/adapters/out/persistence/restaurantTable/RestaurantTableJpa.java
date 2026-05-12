package com.luanferro.reservation_api.adapters.out.persistence.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantTableJpa extends JpaRepository<RestaurantTable, UUID> {
    List<RestaurantTable> findByStatus(StatusTable status);
}
