package ru.nanikon.backend.service;

import org.springframework.stereotype.Service;
import ru.nanikon.backend.entity.UserEntity;
import ru.nanikon.backend.repository.UserRepository;

/**
 * @author Natalia Nikonova
 */
@Service
public class UserService {
   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public UserEntity getUserByLogin(String login) {
      return userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Not found user")); //TODO сделать свой класс исключений NotFound
   }

   public UserEntity getUserById(Long id) {
      return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
   }
}
