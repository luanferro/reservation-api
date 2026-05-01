package com.luanferro.reservation_api.adapters.out.persistence.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantTableImpl implements RestaurantTableRepository {

    private final RestaurantTableJpa restaurantTableJpa;

    @Override
    public List<RestaurantTable> findAll() {
        return restaurantTableJpa.findAll();
    }

    @Override
    public RestaurantTable save(RestaurantTable table) {
        return restaurantTableJpa.save(table);
    }

    @Override
    public List<RestaurantTable> findByStatus(StatusTable status) {
        return restaurantTableJpa.findByStatus(status);
    }
}
