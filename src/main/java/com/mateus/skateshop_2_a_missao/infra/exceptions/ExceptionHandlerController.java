package com.mateus.skateshop_2_a_missao.infra.exceptions;

import com.mateus.skateshop_2_a_missao.domain.user.exceptions.UserAlreadyExistsException;
import com.mateus.skateshop_2_a_missao.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExists(UserAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(ex.getMessage()));
    }

}
