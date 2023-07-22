package com.faol.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data //lombok getters y setters
public class ToDoExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    //constructor
    public ToDoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
