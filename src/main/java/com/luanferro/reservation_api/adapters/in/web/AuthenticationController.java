package com.luanferro.reservation_api.adapters.in.web;

import com.luanferro.reservation_api.adapters.in.security.UserDetailsImpl;
import com.luanferro.reservation_api.application.dto.AuthenticationDTO;
import com.luanferro.reservation_api.application.dto.LoginResponseDTO;
import com.luanferro.reservation_api.config.security.TokenService;
import com.luanferro.reservation_api.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetailsImpl) auth.getPrincipal();
        var token = tokenService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
