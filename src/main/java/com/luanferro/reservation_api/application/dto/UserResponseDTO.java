package com.luanferro.reservation_api.application.dto;

import com.luanferro.reservation_api.domain.enums.UserRole;

import java.util.UUID;

public record UserResponseDTO(UUID id, String nome, String email, UserRole role) {
}
