package com.luanferro.reservation_api.application.usecase.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginUseCase {
    UserDetails loadUserByUsername(String username);
}
