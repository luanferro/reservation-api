package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.adapters.in.security.UserDetailsImpl;
import com.luanferro.reservation_api.application.dto.request.LoginRequest;
import com.luanferro.reservation_api.application.dto.response.LoginResponse;
import com.luanferro.reservation_api.config.security.TokenServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetailsImpl) auth.getPrincipal();
        var token = tokenServiceImpl.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
