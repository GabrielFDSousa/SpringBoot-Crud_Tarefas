package com.gabriel_sousa.crud_tarefas.exception;

import com.gabriel_sousa.crud_tarefas.dto.GlobalExceptionResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleDuplicateKey(DuplicateKeyException ex){
        GlobalExceptionResponseDTO responseDTO =
                new GlobalExceptionResponseDTO(HttpStatus.CONFLICT, ex.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalExceptionResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        GlobalExceptionResponseDTO responseDTO =
                new GlobalExceptionResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
