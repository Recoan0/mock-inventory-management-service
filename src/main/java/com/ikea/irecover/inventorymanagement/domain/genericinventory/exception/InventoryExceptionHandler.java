package com.ikea.irecover.inventorymanagement.domain.genericinventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InventoryExceptionHandler {

    @ExceptionHandler(InventoryEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInventoryEntityNotFoundException(InventoryEntityNotFoundException e) {
        return e.getMessage();
    }

}
