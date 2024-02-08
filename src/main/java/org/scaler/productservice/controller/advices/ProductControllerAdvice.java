package org.scaler.productservice.controller.advices;

import org.scaler.productservice.dtos.ExceptionDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
