package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.domain.model.User;

public interface RegisterUserUseCase {

    User createUser(UserRequest request);
}
