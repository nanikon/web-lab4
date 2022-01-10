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
      return userRepository.findByLogin(login).orElse(null);
   }
}
