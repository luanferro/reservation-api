package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.application.dto.UserRequestDTO;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequestDTO body) {
        User newUser = this.userService.createUser(body);

        return ResponseEntity.ok(newUser);
    }
}
