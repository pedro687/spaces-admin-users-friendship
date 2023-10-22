package com.spaces.user.svc.infrastructure.users;

import com.spaces.user.svc.application.user.UserGateway;
import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.domain.users.UserID;
import com.spaces.user.svc.infrastructure.users.persistence.UserEntityJpa;
import com.spaces.user.svc.infrastructure.users.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMySqlGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserMySqlGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(UserEntityJpa.from(user)).toAggregate();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User findById(UserID id) {
        return null;
    }

    @Override
    public void deactiveUserById(UserID id) {

    }
}
