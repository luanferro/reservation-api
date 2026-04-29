package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.adapters.in.security.UserDetailsImpl;
import com.luanferro.reservation_api.application.dto.request.LoginRequest;
import com.luanferro.reservation_api.application.dto.response.LoginResponse;
import com.luanferro.reservation_api.config.security.TokenServiceImpl;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase{

    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenServiceImpl;

    @Override
    public LoginResponse login(LoginRequest request) {
        var auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        var token = tokenServiceImpl.generateToken(auth.getName());

        return new LoginResponse(token);
    }
}
