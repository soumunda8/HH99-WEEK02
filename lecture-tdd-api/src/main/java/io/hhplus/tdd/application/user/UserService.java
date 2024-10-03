package io.hhplus.tdd.application.user;

import io.hhplus.tdd.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    public UserService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

}