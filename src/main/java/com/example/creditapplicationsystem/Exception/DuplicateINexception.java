package com.example.creditapplicationsystem.Exception;

public class DuplicateINexception extends RuntimeException {
    private static final String GENERIC_MESSAGE = "Identity National Number that has been entered is already exist";

    public DuplicateINexception() {
        super(GENERIC_MESSAGE);
    }
}
