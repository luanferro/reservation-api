package com.luanferro.reservation_api.application.mapper;

import com.luanferro.reservation_api.application.dto.request.RestaurantTableRequest;
import com.luanferro.reservation_api.application.dto.response.RestaurantTableResponse;
import com.luanferro.reservation_api.domain.model.RestaurantTable;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {

    RestaurantTable toEntity(RestaurantTableRequest dto);
    RestaurantTableResponse toResponse(RestaurantTable restaurantTable);
}
