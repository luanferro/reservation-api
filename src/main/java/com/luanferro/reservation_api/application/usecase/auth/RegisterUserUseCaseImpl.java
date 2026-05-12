package com.luanferro.reservation_api.application.usecase.auth;

import com.luanferro.reservation_api.application.dto.request.UserRequest;
import com.luanferro.reservation_api.application.mapper.UserMapper;
import com.luanferro.reservation_api.domain.exception.BusinessException;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserRequest request) {
        validateEmailNotExists(request.email());

        User user = userMapper.toEntity(request);
        user.setSenha(passwordEncoder.encode(request.senha()));

        return repository.save(user);
    }

    private void validateEmailNotExists(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new BusinessException("E-mail already exists " + email);
        }
    }
}
