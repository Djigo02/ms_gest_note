package com.jgohub.ms_bulletin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIException {
    String message;
    HttpStatus status;
    LocalDateTime timestamp;

}
