package com.luanferro.reservation_api.domain.enums;

import lombok.Getter;

@Getter
public enum StatusTable {

    DISPONIVEL("disponivel"),
    RESERVADA("reservada"),
    INATIVA("inativa");

    private final String status;

    StatusTable(String status){
        this.status = status;
    }

    public static StatusTable from(String status) {
        if (status == null) return INATIVA;

        return switch (status.toLowerCase()) {
            case "disponivel" -> DISPONIVEL;
            case "reservada" -> RESERVADA;
            case "invativa" -> INATIVA;
            default -> throw new IllegalArgumentException("Invalid table status: " + status);
        };
    }
}
