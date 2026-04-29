package com.luanferro.reservation_api.adapters.out.persistence;

import com.luanferro.reservation_api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
