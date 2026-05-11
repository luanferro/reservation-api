package com.luanferro.reservation_api.application.usecase.user;

import com.luanferro.reservation_api.domain.exception.NotFoundException;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserUseCaseImplTest {

    @Mock private UserRepository repository;

    @InjectMocks
    private FindUserUseCaseImpl findUserUseCase;

    @Test
    void shouldFindUserByEmailSuccessfully() {
        String email = "luan@email.com";
        User user = new User();
        user.setEmail(email);

        when(repository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = findUserUseCase.findByEmail(email);

        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        String email = "notfound@email.com";

        when(repository.findByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findUserUseCase.findByEmail(email))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User not found");
    }
}