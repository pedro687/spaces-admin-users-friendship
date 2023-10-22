package com.spaces.user.svc.infrastructure.users.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntityJpa, Long> {
}
