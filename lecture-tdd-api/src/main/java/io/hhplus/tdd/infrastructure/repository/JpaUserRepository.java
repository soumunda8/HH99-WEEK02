package io.hhplus.tdd.infrastructure.repository;

import io.hhplus.tdd.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
