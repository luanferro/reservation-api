package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.application.dto.request.LoginRequest;
import com.luanferro.reservation_api.application.dto.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
