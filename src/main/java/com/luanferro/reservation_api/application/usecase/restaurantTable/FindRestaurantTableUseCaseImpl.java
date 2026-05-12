package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindRestaurantTableUseCaseImpl implements FindRestaurantTableUseCase {

    private final RestaurantTableRepository repository;

    @Override
    public List<RestaurantTable> findAll(String status) {

        if (status != null) {
            return repository.findByStatus(StatusTable.from(status));
        }

        return repository.findAll();
    }

    @Override
    public RestaurantTable findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Table not found " + id));
    }
}
