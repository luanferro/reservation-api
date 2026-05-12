package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.model.Reservation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    List<Reservation> findByStartDate(LocalDateTime date);
    boolean existsConflict(UUID tableId, LocalDateTime dataInicio, LocalDateTime dataFim);
    Optional<Reservation> findById(UUID reservationId);
}
