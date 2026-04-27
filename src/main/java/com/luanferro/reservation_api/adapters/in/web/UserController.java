package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.UserRequestDTO;
import com.luanferro.reservation_api.application.dto.UserResponseDTO;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO body) throws Exception {
        User newUser = this.userService.createUser(body);

        UserResponseDTO response = new UserResponseDTO(
                newUser.getId(),
                newUser.getNome(),
                newUser.getEmail(),
                newUser.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
