package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepositoryJpa extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByDate(LocalDateTime date);
    boolean existsByTableIdAndDate(UUID tableId, LocalDateTime date);
}
