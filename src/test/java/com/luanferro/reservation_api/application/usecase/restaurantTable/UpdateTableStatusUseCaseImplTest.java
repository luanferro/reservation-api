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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateTableStatusUseCaseImplTest {

    @Mock private RestaurantTableRepository repository;

    @InjectMocks
    private UpdateTableStatusUseCaseImpl updateTableStatusUseCase;

    @Test
    void shouldUpdateTableStatusSuccessfully() {
        UUID tableId = UUID.randomUUID();
        RestaurantTable table = new RestaurantTable();
        table.setStatus(StatusTable.DISPONIVEL);

        when(repository.findById(tableId)).thenReturn(Optional.of(table));

        updateTableStatusUseCase.updateStatus(tableId, StatusTable.RESERVADA);

        assertThat(table.getStatus()).isEqualTo(StatusTable.RESERVADA);
        verify(repository).save(table);
    }

    @Test
    void shouldThrowWhenTableNotFound() {
        UUID tableId = UUID.randomUUID();

        when(repository.findById(tableId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> updateTableStatusUseCase.updateStatus(tableId, StatusTable.RESERVADA))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Table not found");

        verify(repository, times(0)).save(any());
    }
}