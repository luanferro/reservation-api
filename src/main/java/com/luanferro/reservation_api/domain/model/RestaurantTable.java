package com.luanferro.reservation_api.domain.model;

import com.luanferro.reservation_api.domain.enums.StatusTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "restaurant_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(nullable = false)
    @Min(1)
    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    private StatusTable status;

}
