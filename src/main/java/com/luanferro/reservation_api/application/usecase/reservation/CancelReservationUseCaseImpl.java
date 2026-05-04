package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelReservationUseCaseImpl implements CancelReservationUseCase{

    private final ReservationRepository reservationRepository;

    @Override
    public void cancelReservation(UUID reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        reservation.setReserved(false);

        reservationRepository.save(reservation);
    }
}
