package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public List<Reservation> findByStartDate(LocalDateTime date) {
        return reservationRepositoryJpa.findByStartDate(date);
    }

    @Override
    public boolean existsConflict(UUID tableId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return reservationRepositoryJpa.existsConflict(tableId, dataInicio, dataFim);
    }

    @Override
    public Optional<Reservation> findById(UUID reservationId) {
        return reservationRepositoryJpa.findById(reservationId);
    }
}
