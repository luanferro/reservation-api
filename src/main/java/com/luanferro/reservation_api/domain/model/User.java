package com.luanferro.reservation_api.domain.model;

import com.luanferro.reservation_api.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Table(name = "usuarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
