package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRestaurantUseCaseImpl implements FindAllRestaurantTableUseCase{

    private final RestaurantTableRepository repository;

    @Override
    public List<RestaurantTable> findAll(String status) {

        if (status != null) {
            return repository.findByStatus(StatusTable.from(status));
        }

        return repository.findAll();
    }
}
