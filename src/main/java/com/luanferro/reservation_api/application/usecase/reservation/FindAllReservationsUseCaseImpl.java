package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllReservationsUseCaseImpl implements FindAllReservationsUseCase{

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll(LocalDateTime date) {

        if(date != null) {
            return reservationRepository.findByDate(date);
        }

        return reservationRepository.findAll();
    }
}
