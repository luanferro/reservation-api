package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    List<Reservation> findByDate(LocalDateTime date);
    boolean existsByTableIdAndDate(UUID tableId, LocalDateTime date);
}
