package com.luanferro.reservation_api.adapters.out.persistence.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepositoryJpa extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByStartDate(LocalDateTime date);

    @Query("""
        SELECT COUNT(r) > 0 FROM Reservation r
        WHERE r.table.id = :tableId
          AND r.status = 'ATIVO'
          AND r.startDate < :dataFim
          AND r.endDate > :dataInicio
    """)
    boolean existsConflict(
            @Param("tableId") UUID tableId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim
    );

}
