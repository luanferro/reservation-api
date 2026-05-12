package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.application.dto.request.LoginRequest;
import com.luanferro.reservation_api.application.dto.response.LoginResponse;
import com.luanferro.reservation_api.config.security.TokenServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenServiceImpl tokenServiceImpl;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void shouldLoginSuccessfully() {
        LoginRequest request = new LoginRequest("luan@email.com", "123456");
        Authentication auth = mock(Authentication.class);

        when(auth.getName()).thenReturn("luan@email.com");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(auth);
        when(tokenServiceImpl.generateToken("luan@email.com")).thenReturn("jwt_token");

        LoginResponse response = loginUseCase.login(request);

        assertThat(response).isNotNull();
        assertThat(response.token()).isEqualTo("jwt_token");
    }

    @Test
    void shouldThrowWhenCredentialsAreInvalid() {
        LoginRequest request = new LoginRequest("luan@email.com", "wrong_password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThatThrownBy(() -> loginUseCase.login(request))
                .isInstanceOf(BadCredentialsException.class);
    }
}