package ru.nanikon.backend.service;

import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.exception.NotFoundDataException;
import ru.nanikon.backend.mapper.UserMapper;
import ru.nanikon.backend.repository.UserRepository;

/**
 * @author Natalia Nikonova
 */
@Service
public class UserService {
   private final UserRepository userRepository;
   private final UserMapper mapper;

   public UserService(UserRepository userRepository, UserMapper mapper) {
      this.userRepository = userRepository;
      this.mapper = mapper;
   }

   public UserDTO getUserByLogin(String login) {
      return mapper.fromEntityToDTO(userRepository.findByLogin(login).orElseThrow(() -> new NotFoundDataException("Не найден пользователь с логином " + login)));
   }

   public Long getIdByLogin(String login) {
      return userRepository.findByLogin(login).orElseThrow(() -> new NotFoundDataException("Не найден пользователь с логином " + login)).getId();
   }

   public UserDTO getUserById(Long id) {
      return mapper.fromEntityToDTO(userRepository.findById(id).orElseThrow(() -> new NotFoundDataException("Не найден пользователь с id " + id)));
   }

   public UserDTO create(UserDTO user) {
      return mapper.fromEntityToDTO(userRepository.save(mapper.fromDTOToEntity(user)));
   }
}
