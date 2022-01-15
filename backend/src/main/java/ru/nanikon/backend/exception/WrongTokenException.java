package ru.nanikon.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Natalia Nikonova
 */
public class WrongTokenException extends BaseApiException{
   public WrongTokenException(String message) {
      super(message, "WRONG_TOKEN_ERROR", HttpStatus.UNAUTHORIZED);
   }
}
