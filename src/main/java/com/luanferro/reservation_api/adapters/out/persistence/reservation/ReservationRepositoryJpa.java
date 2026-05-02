package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReservationRepositoryJpa extends JpaRepository<Reservation, UUID> {
}
