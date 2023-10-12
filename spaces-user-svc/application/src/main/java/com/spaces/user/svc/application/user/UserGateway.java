package com.spaces.user.svc.application.user;

import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.domain.users.UserID;

public interface UserGateway {
    User save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User updateUser(User user);

    User findById(UserID id);

    void deactiveUserById(UserID id);

}
