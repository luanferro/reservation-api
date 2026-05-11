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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static com.luanferro.reservation_api.config.BusinessConfig.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookTableUseCaseImplTest {

    @Mock private ReservationMapper mapper;
    @Mock private ReservationRepository reservationRepository;
    @Mock private FindRestaurantTableUseCase findRestaurantTableUseCase;
    @Mock private UpdateTableStatusUseCase updateTableStatusUseCase;
    @Mock private FindUserUseCase findUserUseCase;
    @Mock private SecurityContext securityContext;

    @InjectMocks
    private BookTableUseCaseImpl bookTableUseCase;

    private ReservationRequest validRequest() {
        return new ReservationRequest(
                UUID.randomUUID(),
                LocalDateTime.now().withHour(20).withMinute(0),
                4
        );
    }

    private RestaurantTable activeTable(int capacidade) {
        RestaurantTable table = new RestaurantTable();
        table.setStatus(StatusTable.DISPONIVEL);
        table.setCapacidade(capacidade);
        return table;
    }

    @Test
    void shouldBookTableSuccessfully() {
        ReservationRequest request = validRequest();
        RestaurantTable table = activeTable(4);
        User user = new User();
        Reservation reservation = new Reservation();

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(false);
        when(findRestaurantTableUseCase.findById(request.table())).thenReturn(table);
        when(securityContext.getCurrentUserSubject()).thenReturn("luan@email.com");
        when(findUserUseCase.findByEmail("luan@email.com")).thenReturn(user);
        when(mapper.toEntity(request)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = bookTableUseCase.book(request);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(StatusReservation.ATIVO);
        verify(reservationRepository).save(reservation);
        verify(updateTableStatusUseCase).updateStatus(request.table(), StatusTable.RESERVADA);
    }

    @Test
    void shouldThrowWhenOutsideBusinessHours() {
        ReservationRequest request = new ReservationRequest(
                UUID.randomUUID(),
                LocalDateTime.now().withHour(6).withMinute(0),
                4
        );

        assertThatThrownBy(() -> bookTableUseCase.book(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("fora do horario de funcionamento");

        verifyNoInteractions(reservationRepository);
    }

    @Test
    void shouldThrowWhenTableHasConflict() {
        ReservationRequest request = validRequest();

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(true);

        assertThatThrownBy(() -> bookTableUseCase.book(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("não disponível");

        verify(reservationRepository, times(0)).save(any());
    }

    @Test
    void shouldThrowWhenTableIsInactive() {
        ReservationRequest request = validRequest();
        RestaurantTable table = new RestaurantTable();
        table.setStatus(StatusTable.INATIVA);
        table.setCapacidade(4);

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(false);
        when(findRestaurantTableUseCase.findById(request.table())).thenReturn(table);

        assertThatThrownBy(() -> bookTableUseCase.book(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("não disponivel para reserva");
    }

    @Test
    void shouldThrowWhenTableCapacityIsInsufficient() {
        ReservationRequest request = new ReservationRequest(
                UUID.randomUUID(),
                LocalDateTime.now().withHour(20).withMinute(0),
                10
        );
        RestaurantTable table = activeTable(4);

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(false);
        when(findRestaurantTableUseCase.findById(request.table())).thenReturn(table);

        assertThatThrownBy(() -> bookTableUseCase.book(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("capacidade");
    }
}