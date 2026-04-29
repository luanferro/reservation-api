package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.application.dto.response.UserResponse;
import com.luanferro.reservation_api.application.mapper.UserMapper;
import com.luanferro.reservation_api.application.usecase.user.CreateUserUseCase;
import com.luanferro.reservation_api.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest body) throws Exception {
        User newUser = this.createUserUseCase.createUser(body);

        UserResponse response = userMapper.toResponse(newUser);

        return ResponseEntity.ok(response);
    }
}
