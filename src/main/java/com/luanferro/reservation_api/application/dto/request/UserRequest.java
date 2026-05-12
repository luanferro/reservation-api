package com.luanferro.reservation_api.application.dto.request;

import com.luanferro.reservation_api.domain.enums.UserRole;

public record UserRequest(String nome, String email, String senha, UserRole role) {
}
