package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.application.mapper.UserMapper;
import com.luanferro.reservation_api.domain.enums.UserRole;
import com.luanferro.reservation_api.domain.exception.BusinessException;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;

    @Test
    void shouldRegisterUserSuccessfully() {
        UserRequest request = new UserRequest("Luan", "luan@email.com", "123456", UserRole.USER);
        User user = new User();
        user.setEmail("luan@email.com");

        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode(request.senha())).thenReturn("hashed_password");
        when(repository.save(user)).thenReturn(user);

        User result = registerUserUseCase.createUser(request);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("luan@email.com");
        verify(repository).save(user);
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        UserRequest request = new UserRequest("Luan", "luan@email.com", "123456", UserRole.USER);

        when(repository.findByEmail(request.email())).thenReturn(Optional.of(new User()));

        assertThatThrownBy(() -> registerUserUseCase.createUser(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("E-mail already exists");

        verify(repository, never()).save(any());
    }

    @Test
    void shouldEncodePasswordBeforeSaving() {
        UserRequest request = new UserRequest("Luan", "luan@email.com", "123456", UserRole.USER);
        User user = new User();

        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode("123456")).thenReturn("hashed_password");
        when(repository.save(user)).thenReturn(user);

        registerUserUseCase.createUser(request);

        assertThat(user.getSenha()).isEqualTo("hashed_password");
        verify(passwordEncoder).encode("123456");
    }
}