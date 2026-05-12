package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.application.usecase.restaurantTable.UpdateTableStatusUseCase;
import com.luanferro.reservation_api.domain.enums.StatusReservation;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelReservationUseCaseImpl implements CancelReservationUseCase{

    private final ReservationRepository reservationRepository;
    private final UpdateTableStatusUseCase updateTableStatusUseCase;

    @Transactional
    @Override
    public void cancelReservation(UUID reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        reservation.setStatus(StatusReservation.CANCELADO);
        reservationRepository.save(reservation);

        updateTableStatusUseCase.updateStatus(reservation.getTable().getId(), StatusTable.DISPONIVEL);
    }
}
