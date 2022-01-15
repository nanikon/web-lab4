package ru.nanikon.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Natalia Nikonova
 */
public class NotFoundDataException extends BaseApiException {
   public NotFoundDataException(String message) {
      super(message, "NOT_FOUND_DATA_ERROR", HttpStatus.BAD_REQUEST);
   }
}
