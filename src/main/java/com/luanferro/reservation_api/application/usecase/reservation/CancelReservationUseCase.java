package com.luanferro.reservation_api.application.usecase.reservation;

import java.util.UUID;

public interface CancelReservationUseCase {
    void cancelReservation(UUID reservationId);
}
