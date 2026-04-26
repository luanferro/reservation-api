package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
