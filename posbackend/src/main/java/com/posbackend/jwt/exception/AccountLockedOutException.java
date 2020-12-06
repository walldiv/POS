package com.posbackend.jwt.exception;

public class AccountLockedOutException extends Exception {
    public AccountLockedOutException(String message) {super(message);}
}
