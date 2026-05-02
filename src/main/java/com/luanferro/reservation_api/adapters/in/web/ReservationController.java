package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.request.ReservationRequest;
import com.luanferro.reservation_api.application.dto.response.ReservationResponse;
import com.luanferro.reservation_api.application.mapper.ReservationMapper;
import com.luanferro.reservation_api.application.usecase.reservation.BookTableUseCase;
import com.luanferro.reservation_api.domain.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationMapper mapper;
    private final BookTableUseCase bookTableUseCase;

    @PostMapping
    public ResponseEntity<ReservationResponse> book(@RequestBody ReservationRequest request) {

        Reservation reservation = bookTableUseCase.book(request);

        ReservationResponse response = mapper.toResponse(reservation);

        return ResponseEntity.ok(response);
    }
}
