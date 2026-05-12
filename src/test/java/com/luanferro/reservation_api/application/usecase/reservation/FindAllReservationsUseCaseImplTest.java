package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllReservationsUseCaseImplTest {

    @Mock private ReservationRepository reservationRepository;

    @InjectMocks
    private FindAllReservationsUseCaseImpl findAllReservationsUseCase;

    @Test
    void shouldReturnAllReservationsWhenNoDateProvided() {
        List<Reservation> reservations = List.of(new Reservation(), new Reservation());

        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = findAllReservationsUseCase.findAll(null);

        assertThat(result).hasSize(2);
        verify(reservationRepository).findAll();
        verify(reservationRepository, times(0)).findByStartDate(any());
    }

    @Test
    void shouldReturnFilteredReservationsWhenDateProvided() {
        LocalDateTime date = LocalDateTime.now();
        List<Reservation> reservations = List.of(new Reservation());

        when(reservationRepository.findByStartDate(date)).thenReturn(reservations);

        List<Reservation> result = findAllReservationsUseCase.findAll(date);

        assertThat(result).hasSize(1);
        verify(reservationRepository).findByStartDate(date);
        verify(reservationRepository, times(0)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationsFound() {
        when(reservationRepository.findAll()).thenReturn(List.of());

        List<Reservation> result = findAllReservationsUseCase.findAll(null);

        assertThat(result).isEmpty();
    }
}