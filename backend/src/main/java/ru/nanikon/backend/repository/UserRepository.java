package ru.nanikon.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nanikon.backend.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
