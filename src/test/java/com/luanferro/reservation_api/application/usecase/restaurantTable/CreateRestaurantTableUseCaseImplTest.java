package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.application.dto.request.RestaurantTableRequest;
import com.luanferro.reservation_api.application.mapper.RestaurantTableMapper;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantTableUseCaseImplTest {

    @Mock private RestaurantTableRepository repository;
    @Mock private RestaurantTableMapper mapper;

    @InjectMocks
    private CreateRestaurantTableUseCaseImpl createRestaurantTableUseCase;

    @Test
    void shouldCreateTableWithStatusDisponivel() {
        RestaurantTableRequest request = new RestaurantTableRequest("Mesa 1", 4);
        RestaurantTable table = new RestaurantTable();

        when(mapper.toEntity(request)).thenReturn(table);
        when(repository.save(table)).thenReturn(table);

        RestaurantTable result = createRestaurantTableUseCase.create(request);

        assertThat(result.getStatus()).isEqualTo(StatusTable.DISPONIVEL);
        verify(repository).save(table);
    }
}