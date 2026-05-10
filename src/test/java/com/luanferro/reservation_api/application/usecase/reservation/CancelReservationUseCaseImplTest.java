package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.application.usecase.restaurantTable.UpdateTableStatusUseCase;
import com.luanferro.reservation_api.domain.enums.StatusReservation;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
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
class CancelReservationUseCaseImplTest {

    @Mock private ReservationRepository reservationRepository;
    @Mock private UpdateTableStatusUseCase updateTableStatusUseCase;

    @InjectMocks
    private CancelReservationUseCaseImpl cancelReservationUseCase;

    @Test
    void shouldCancelReservationSuccessfully() {
        UUID reservationId = UUID.randomUUID();
        RestaurantTable table = new RestaurantTable();
        table.setId(UUID.randomUUID());

        Reservation reservation = new Reservation();
        reservation.setStatus(StatusReservation.ATIVO);
        reservation.setTable(table);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        cancelReservationUseCase.cancelReservation(reservationId);

        assertThat(reservation.getStatus()).isEqualTo(StatusReservation.CANCELADO);
        verify(reservationRepository).save(reservation);
        verify(updateTableStatusUseCase).updateStatus(table.getId(), StatusTable.DISPONIVEL);
    }

    @Test
    void shouldThrowWhenReservationNotFound() {
        UUID reservationId = UUID.randomUUID();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cancelReservationUseCase.cancelReservation(reservationId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Reservation not found");

        verify(reservationRepository, times(0)).save(any());
        verifyNoInteractions(updateTableStatusUseCase);
    }
}