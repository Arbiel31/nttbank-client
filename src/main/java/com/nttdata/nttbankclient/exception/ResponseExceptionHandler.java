package com.nttdata.nttbankclient.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exception.
 *
 * @author Percy
 */
@ControllerAdvice
@RestController
public class ResponseExceptionHandler {

  /**
   * ExceptionHandler.
   *
   * @author Percy
   */
  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<ExceptionResponse> allException(CustomException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
            ex.getMessage());
    return new ResponseEntity<ExceptionResponse>(exceptionResponse,
            HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
