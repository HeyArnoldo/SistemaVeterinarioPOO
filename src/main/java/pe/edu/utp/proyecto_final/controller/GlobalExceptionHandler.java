package pe.edu.utp.proyecto_final.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.edu.utp.proyecto_final.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
