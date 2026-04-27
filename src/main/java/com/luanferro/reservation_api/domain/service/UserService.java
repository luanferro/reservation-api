package com.luanferro.reservation_api.domain.service;

import com.luanferro.reservation_api.application.dto.UserRequestDTO;
import com.luanferro.reservation_api.application.mapper.UserMapper;
import com.luanferro.reservation_api.domain.enums.UserRole;
import com.luanferro.reservation_api.domain.model.User;
import com.luanferro.reservation_api.domain.port.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public User createUser(UserRequestDTO data) throws Exception {

        validateEmailNotExists(data.email());

        User user = userMapper.toEntity(data);
        user.setSenha(passwordEncoder.encode(data.senha()));

        return repository.save(user);
    }

    private void validateEmailNotExists(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail already exists");
        }
    }

}
