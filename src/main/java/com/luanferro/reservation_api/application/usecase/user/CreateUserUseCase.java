package com.luanferro.reservation_api.application.usecase.user;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.domain.model.User;

public interface CreateUserUseCase {

    User createUser(UserRequest request);
}
