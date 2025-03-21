package com.ikea.irecover.inventorymanagement.domain.articleinventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleInventoryExceptionHandler {

    @ExceptionHandler(ArticlePartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleArticlePartNotFoundException(ArticlePartNotFoundException ex) {
        return ex.getMessage();
    }
}
