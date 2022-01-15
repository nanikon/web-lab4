package ru.nanikon.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Natalia Nikonova
 */
public class UserAlreadyExistException extends BaseApiException{
   public UserAlreadyExistException(String message) {
      super(message, "USER_ALREADY_EXIST_ERROR", HttpStatus.BAD_REQUEST);
   }
}
