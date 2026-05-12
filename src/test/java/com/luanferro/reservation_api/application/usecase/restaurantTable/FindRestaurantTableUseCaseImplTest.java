package com.luanferro.reservation_api.application.usecase.restaurantTable;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindRestaurantTableUseCaseImplTest {

    @Mock private RestaurantTableRepository repository;

    @InjectMocks
    private FindRestaurantTableUseCaseImpl findRestaurantTableUseCase;

    @Test
    void shouldReturnAllTablesWhenNoStatusProvided() {
        List<RestaurantTable> tables = List.of(new RestaurantTable(), new RestaurantTable());

        when(repository.findAll()).thenReturn(tables);

        List<RestaurantTable> result = findRestaurantTableUseCase.findAll(null);

        assertThat(result).hasSize(2);
        verify(repository).findAll();
        verify(repository, times(0)).findByStatus(any());
    }

    @Test
    void shouldReturnFilteredTablesWhenStatusProvided() {
        List<RestaurantTable> tables = List.of(new RestaurantTable());

        when(repository.findByStatus(StatusTable.DISPONIVEL)).thenReturn(tables);

        List<RestaurantTable> result = findRestaurantTableUseCase.findAll("DISPONIVEL");

        assertThat(result).hasSize(1);
        verify(repository).findByStatus(StatusTable.DISPONIVEL);
        verify(repository, times(0)).findAll();
    }

    @Test
    void shouldFindTableByIdSuccessfully() {
        UUID id = UUID.randomUUID();
        RestaurantTable table = new RestaurantTable();
        table.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(table));

        RestaurantTable result = findRestaurantTableUseCase.findById(id);

        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void shouldThrowWhenTableNotFoundById() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findRestaurantTableUseCase.findById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Table not found");
    }
}