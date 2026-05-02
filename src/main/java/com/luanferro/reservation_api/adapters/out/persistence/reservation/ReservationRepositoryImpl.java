package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationRepositoryJpa reservationRepositoryJpa;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepositoryJpa.save(reservation);
    }
}
