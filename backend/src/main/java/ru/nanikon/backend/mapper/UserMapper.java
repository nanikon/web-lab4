package ru.nanikon.backend.mapper;

import org.springframework.stereotype.Component;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.entity.UserEntity;

/**
 * @author Natalia Nikonova
 */
@Component
public class UserMapper {
   public UserEntity fromDTOToEntity(UserDTO source) {
      UserEntity target = new UserEntity();
      target.setLogin(source.getLogin());
      target.setPassword(source.getPassword());
      return target;
   }

   public UserDTO fromEntityToDTO(UserEntity source) {
      UserDTO target = new UserDTO();
      target.setLogin(source.getLogin());
      target.setPassword(source.getPassword());
      return target;
   }
}
