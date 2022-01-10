package ru.nanikon.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nanikon.backend.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}