package com.luanferro.reservation_api.domain.port.out;

import com.luanferro.reservation_api.domain.model.User;
import java.util.Optional;

public interface UserRepository{
    User save(User user);
    Optional<User> findByEmail(String email);

}
