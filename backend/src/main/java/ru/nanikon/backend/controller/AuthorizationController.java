package ru.nanikon.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nanikon.backend.dto.UserDTO;

/**
 * @author Natalia Nikonova
 */
@RestController
@RequestMapping("/api/users")
public class AuthorizationController {

   @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
      return null;
   }

   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> registration(@RequestBody UserDTO userDTO) {
      return null;
   }
}
