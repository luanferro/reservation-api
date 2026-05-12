package com.luanferro.reservation_api.application.usecase.user;

import com.luanferro.reservation_api.domain.model.User;

import java.util.UUID;

public interface FindUserUseCase {
    User findByEmail(String email);
}
