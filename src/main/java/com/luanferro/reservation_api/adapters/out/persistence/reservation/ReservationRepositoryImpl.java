package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationRepositoryJpa reservationRepositoryJpa;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepositoryJpa.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepositoryJpa.findAll();
    }

    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        return reservationRepositoryJpa.findByDate(date);
    }

    @Override
    public boolean existsByTableIdAndDate(UUID tableId, LocalDateTime date) {
        return reservationRepositoryJpa.existsByTableIdAndDate(tableId, date);
    }
}
