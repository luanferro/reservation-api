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
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        if(reservationRepository.existsByTableIdAndDate(request.table(), request.date())){
            throw new BusinessException("Não foi possivel realizar a reserva. Mesa não disponivel para esta data: " + request.date());
        }

        RestaurantTable table = findRestaurantTableUseCase.findById(request.table());

        if(table.getStatus() != StatusTable.DISPONIVEL) {
            throw new BusinessException("Mesa não disponivel para reserva");
        }

        if(table.getCapacidade() < request.peopleQuantity()) {
            throw new BusinessException("A capacidade da mesa nao suporta quantidade de pessoas " + request.peopleQuantity());
        }

        User user = findUserUseCase.findByEmail(securityContext.getCurrentUserSubject());

        Reservation reservation = mapper.toEntity(request);
        reservation.setTable(table);
        reservation.setUser(user);
        reservation.setStatus(StatusReservation.ATIVO);

        reservationRepository.save(reservation);
        updateTableStatusUseCase.updateStatus(request.table(), StatusTable.RESERVADA);

        return reservation;
    }
}
