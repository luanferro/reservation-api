package com.luanferro.reservation_api.domain.enums;

import lombok.Getter;

@Getter
public enum StatusReservation {

    ATIVO("ativo"),
    CANCELADO("cancelado");

    private final String status;

    StatusReservation(String status){this.status = status;}

    public static StatusReservation from(String status) {
        if(status == null) return CANCELADO;

        return switch (status.toLowerCase()) {
            case "ativo" -> ATIVO;
            case "cancelado" -> CANCELADO;
            default -> throw new IllegalArgumentException("Invalid reservation status " + status);
        };
    }
}
