package com.nttdata.nttbankclient.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {
  private LocalDateTime date;
  private String message;
}
