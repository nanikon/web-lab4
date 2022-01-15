package ru.nanikon.backend.service;

import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.UserDTO;
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
      return mapper.fromEntityToDTO(userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Not found user"))); //TODO сделать свой класс исключений NotFound
   }

   public Long getIdByLogin(String login) {
      return userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Not found user")).getId();
   }

   public UserDTO getUserById(Long id) {
      return mapper.fromEntityToDTO(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user")));
   }

   public UserDTO create(UserDTO user) {
      return mapper.fromEntityToDTO(userRepository.save(mapper.fromDTOToEntity(user)));
   }
}
