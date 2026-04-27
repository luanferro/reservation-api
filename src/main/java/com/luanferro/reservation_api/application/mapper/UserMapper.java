package com.luanferro.reservation_api.application.mapper;

import com.luanferro.reservation_api.application.dto.UserRequestDTO;
import com.luanferro.reservation_api.application.dto.UserResponseDTO;
import com.luanferro.reservation_api.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "senha", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(User user);
}
