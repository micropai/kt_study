package com.example.springbasic.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{

    private HttpStatus httpStatus;

    public NotFoundException() {
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

}

