package com.net.product.infraestructure.in.advice;

import com.net.product.domain.dto.rest.ErrorResponseDto;
import com.net.product.infraestructure.in.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ProductControllerRestAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(ObjectNotFoundException exception){
        log.warn("[ADVICE] Error - Not Found");
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(),HttpStatus.NOT_FOUND.value(),Constants.NOT_FOUND_OBJECT_ERROR );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleException(RuntimeException exception){
        log.warn("[ADVICE] Error - Internal Server Error");
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(),Constants.GENERIC_ERROR );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

}
