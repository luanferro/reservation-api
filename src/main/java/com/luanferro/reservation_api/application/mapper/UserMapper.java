package com.luanferro.reservation_api.application.mapper;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.application.dto.response.UserResponse;
import com.luanferro.reservation_api.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "senha", ignore = true)
    User toEntity(UserRequest dto);

    UserResponse toResponse(User user);
}
