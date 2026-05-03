package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.dto.response.ReservationResponse;
import com.luanferro.reservation_api.application.dto.response.ReservationSummaryResponse;
import com.luanferro.reservation_api.application.mapper.ReservationMapper;
import com.luanferro.reservation_api.application.mapper.ReservationSummaryMapper;
import com.luanferro.reservation_api.application.usecase.reservation.BookTableUseCase;
import com.luanferro.reservation_api.application.usecase.reservation.CancelReservationUseCase;
import com.luanferro.reservation_api.application.usecase.reservation.FindAllReservationsUseCase;
import com.luanferro.reservation_api.domain.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationMapper mapper;
    private final ReservationSummaryMapper summaryMapper;
    private final BookTableUseCase bookTableUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final FindAllReservationsUseCase findAllReservationsUseCase;

    @PostMapping
    public ResponseEntity<ReservationResponse> book(@RequestBody ReservationRequest request) {

        Reservation reservation = bookTableUseCase.book(request);

        ReservationResponse response = mapper.toResponse(reservation);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{reservationId}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable("reservationId") UUID reservationId) {

        cancelReservationUseCase.cancelReservation(reservationId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationSummaryResponse>> findAll(@RequestParam(required = false) LocalDateTime date){

        List<Reservation> reservations = findAllReservationsUseCase.findAll(date);

        return ResponseEntity.ok(reservations.stream()
                .map(summaryMapper::toResponse)
                .toList());
    }
}
