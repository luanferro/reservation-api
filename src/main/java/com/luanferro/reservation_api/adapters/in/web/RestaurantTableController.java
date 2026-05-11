package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.request.RestaurantTableRequest;
import com.luanferro.reservation_api.application.dto.response.RestaurantTableResponse;
import com.luanferro.reservation_api.application.mapper.RestaurantTableMapper;
import com.luanferro.reservation_api.application.usecase.restaurantTable.CreateRestaurantTableUseCase;
import com.luanferro.reservation_api.application.usecase.restaurantTable.FindRestaurantTableUseCase;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final CreateRestaurantTableUseCase createRestaurantTableUseCase;
    private final FindRestaurantTableUseCase findAllRestaurantTableUseCase;
    private final RestaurantTableMapper mapper;

    @PostMapping
    public ResponseEntity<RestaurantTableResponse> create(@RequestBody @Valid RestaurantTableRequest request) {

        RestaurantTable newRestaurantTable = createRestaurantTableUseCase.create(request);

        RestaurantTableResponse response = mapper.toResponse(newRestaurantTable);

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantTableResponse>> findAll(@RequestParam(required = false) String status) {

        List<RestaurantTable> restaurantTables = findAllRestaurantTableUseCase.findAll(status);

        return ResponseEntity.ok(restaurantTables.stream()
                .map(mapper::toResponse)
                .toList());
    }
}
