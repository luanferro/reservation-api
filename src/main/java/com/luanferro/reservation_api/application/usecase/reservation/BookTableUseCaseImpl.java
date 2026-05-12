package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.adapters.in.security.SecurityContext;
import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.mapper.ReservationMapper;
import com.luanferro.reservation_api.application.usecase.restaurantTable.FindRestaurantTableUseCase;
import com.luanferro.reservation_api.application.usecase.restaurantTable.UpdateTableStatusUseCase;
import com.luanferro.reservation_api.application.usecase.user.FindUserUseCase;
import com.luanferro.reservation_api.domain.enums.StatusReservation;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.BusinessException;
import com.luanferro.reservation_api.domain.exception.ConflictException;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.luanferro.reservation_api.config.BusinessConfig.*;

@Service
@RequiredArgsConstructor
public class BookTableUseCaseImpl implements BookTableUseCase{

    private final ReservationMapper mapper;
    private final ReservationRepository reservationRepository;
    private final FindRestaurantTableUseCase findRestaurantTableUseCase;
    private final UpdateTableStatusUseCase updateTableStatusUseCase;
    private final FindUserUseCase findUserUseCase;
    private final SecurityContext securityContext;

    @Transactional
    @Override
    public Reservation book(ReservationRequest request) {

        LocalDateTime endDate = request.startDate().plusHours(RESERVATION_DURATION_HOURS);
        LocalTime horario = request.startDate().toLocalTime();

        if (horario.isBefore(RESTAURANT_OPEN) || horario.isAfter(RESTAURANT_CLOSED)) {
            throw new BusinessException("Horario solicitado fora do horario de funcionamento");
        }

        if(reservationRepository.existsConflict(request.table(),
                                                request.startDate(),
                                                endDate
                                                )){
            throw new ConflictException("Mesa não disponível para o horário solicitado");
        }

        RestaurantTable table = findRestaurantTableUseCase.findById(request.table());

        if(table.getStatus() == StatusTable.INATIVA) {
            throw new BusinessException("Mesa não disponivel para reserva");
        }

        if(table.getCapacidade() < request.peopleQuantity()) {
            throw new BusinessException("A capacidade da mesa nao suporta quantidade de pessoas");
        }

        User user = findUserUseCase.findByEmail(securityContext.getCurrentUserSubject());

        Reservation reservation = mapper.toEntity(request);
        reservation.setTable(table);
        reservation.setUser(user);
        reservation.setEndDate(endDate);
        reservation.setStatus(StatusReservation.ATIVO);

        reservationRepository.save(reservation);

        updateTableStatusUseCase.updateStatus(request.table(), StatusTable.RESERVADA);

        return reservation;
    }
}
