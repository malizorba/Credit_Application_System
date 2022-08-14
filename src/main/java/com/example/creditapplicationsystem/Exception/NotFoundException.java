package com.example.creditapplicationsystem.Exception;

public class NotFoundException extends RuntimeException{

    String detail;
    public NotFoundException(String message) {
        super(message + " not found!");
    }


}
