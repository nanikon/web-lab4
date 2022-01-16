package ru.nanikon.backend.security.userDetails;

import org.springframework.stereotype.Component;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.entity.UserEntity;

/**
 * @author Natalia Nikonova
 */
@Component
public class UserToCustomUserDetailsMapper {
   public CustomUserDetails fromDTOToDetails(UserDTO userDTO) {
      CustomUserDetails result = new CustomUserDetails();
      result.setUsername(userDTO.getLogin());
      result.setPassword(userDTO.getPassword());
      return result;
   }

   public CustomUserDetails fromEntityToDetails(UserEntity userEntity) {
      CustomUserDetails result = new CustomUserDetails();
      result.setId(userEntity.getId());
      result.setUsername(userEntity.getLogin());
      result.setPassword(userEntity.getPassword());
      return result;
   }

   public UserDTO fromDetailsToDTO(CustomUserDetails userDetails) {
      UserDTO result = new UserDTO();
      result.setLogin(userDetails.getUsername());
      result.setPassword(userDetails.getPassword());
      return result;
   }
}
