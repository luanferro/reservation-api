package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTableStatusUseCaseImpl implements UpdateTableStatusUseCase{

    private final RestaurantTableRepository repository;

    @Override
    public void updateStatus(UUID tableId, StatusTable status) {
        RestaurantTable table = repository.findById(tableId)
                .orElseThrow(() -> new NotFoundException("Table not found " + tableId));

        table.setStatus(status);
        repository.save(table);
    }
}
