package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.model.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
}
