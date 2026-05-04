package com.luanferro.reservation_api.application.usecase.reservation;

import com.luanferro.reservation_api.adapters.in.security.SecurityContext;
import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.mapper.ReservationMapper;
import com.luanferro.reservation_api.domain.enums.StatusTable;
import com.luanferro.reservation_api.domain.exception.BusinessException;
import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.Reservation;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.ReservationRepository;
import com.luanferro.reservation_api.domain.port.out.RestaurantTableRepository;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookTableUseCaseImpl implements BookTableUseCase{

    private final ReservationMapper mapper;
    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Override
    public Reservation book(ReservationRequest request) {

        if(reservationRepository.existsByTableIdAndDate(request.table(), request.date())){
            throw new BusinessException("Não foi possivel realizar a reserva. Mesa não disponivel para esta data: " + request.date());
        }

        RestaurantTable table = restaurantTableRepository.findById(request.table())
                .orElseThrow(() -> new NotFoundException("Table not found"));

        String userSubject = securityContext.getCurrentUserSubject();

        User user = userRepository.findByEmail(userSubject)
                .orElseThrow(() -> new NotFoundException("User not found " + userSubject));

        Reservation reservation = mapper.toEntity(request);
        reservation.setTable(table);
        reservation.setUser(user);
        reservation.setReserved(true);

        return reservationRepository.save(reservation);
    }
}
