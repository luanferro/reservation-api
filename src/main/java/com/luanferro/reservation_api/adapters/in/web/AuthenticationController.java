package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.request.LoginRequest;
import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.application.dto.response.LoginResponse;
import com.luanferro.reservation_api.application.dto.response.UserResponse;
import com.luanferro.reservation_api.application.mapper.UserMapper;
import com.luanferro.reservation_api.application.usecase.auth.LoginUseCase;
import com.luanferro.reservation_api.application.usecase.auth.RegisterUserUseCase;
import com.luanferro.reservation_api.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase createUserUseCase;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest body) throws Exception {
        User newUser = this.createUserUseCase.createUser(body);

        UserResponse response = userMapper.toResponse(newUser);

        return ResponseEntity.ok(response);
    }
}
