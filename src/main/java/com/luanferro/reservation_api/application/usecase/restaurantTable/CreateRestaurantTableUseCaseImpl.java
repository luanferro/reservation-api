package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.application.dto.request.RestaurantTableRequest;
import com.luanferro.reservation_api.application.mapper.RestaurantTableMapper;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRestaurantTableUseCaseImpl implements CreateRestaurantTableUseCase{

    private final RestaurantTableRepository repository;
    private final RestaurantTableMapper mapper;

    @Override
    public RestaurantTable create(RestaurantTableRequest request) {

        RestaurantTable restaurantTable = mapper.toEntity(request);

        restaurantTable.setStatus(StatusTable.DISPONIVEL);

        return repository.save(restaurantTable);
    }
}
