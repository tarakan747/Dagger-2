package com.telran.a23_03_20.business.auth;

public class PasswordValidException extends RuntimeException {
    public PasswordValidException(String message) {
        super(message);
    }
}
