package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface FindAllReservationsUseCase {
    List<Reservation> findAll(LocalDateTime date);
}
