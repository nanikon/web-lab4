package ru.nanikon.backend.data;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Natalia Nikonova
 */
@Getter
@Setter
public class UserDTO {
   @NotBlank
   private String login;
   @NotBlank
   private String password;
}
