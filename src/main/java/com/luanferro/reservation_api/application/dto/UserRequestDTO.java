package com.luanferro.reservation_api.application.dto;

public record UserRequestDTO(String nome, String email, String senha, String role) {
}
