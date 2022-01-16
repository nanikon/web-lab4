package ru.nanikon.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nanikon.backend.data.TokenDTO;
import ru.nanikon.backend.data.UserDTO;
import ru.nanikon.backend.service.AuthorizationService;

import javax.validation.Valid;

/**
 * @author Natalia Nikonova
 */
@RestController
@RequestMapping("/api/users")
public class AuthorizationController {
   private final AuthorizationService authorizationService;

   public AuthorizationController(AuthorizationService authorizationService) {
      this.authorizationService = authorizationService;
   }

   @Operation(summary = "Метод для авторизации пользователя")
   @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public TokenDTO login(@RequestBody @Valid UserDTO userDTO) {
      return authorizationService.login(userDTO);
   }

   @Operation(summary = "Метод для регистрации нового пользователя")
   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public UserDTO registration(@RequestBody @Valid UserDTO userDTO) {
      return authorizationService.register(userDTO);
   }
}
