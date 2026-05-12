package com.luanferro.reservation_api.application.dto.response;

import com.luanferro.reservation_api.domain.enums.UserRole;
import java.util.UUID;

public record UserResponse(UUID id, String nome, String email, UserRole role) {
}
