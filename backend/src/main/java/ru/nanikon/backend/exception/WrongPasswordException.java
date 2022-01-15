package ru.nanikon.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Natalia Nikonova
 */
public class WrongPasswordException extends BaseApiException {
   public WrongPasswordException(String message) {
      super(message, "WRONG_PASSWORD_ERROR", HttpStatus.UNAUTHORIZED);
   }
}
