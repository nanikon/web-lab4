package ru.nanikon.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nanikon.backend.entity.ShotEntity;
import ru.nanikon.backend.entity.UserEntity;

import java.util.List;

public interface ShotRepository extends CrudRepository<ShotEntity, Long> {
    List<ShotEntity> getAllByUser(UserEntity User);
}