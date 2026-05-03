package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.domain.model.Reservation;

public interface BookTableUseCase {
    Reservation book(ReservationRequest request);
}
