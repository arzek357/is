package com.mtuci.bsu1701.is.exceptions.securityExceptions.exceptions;

import com.mtuci.bsu1701.is.exceptions.securityExceptions.SecurityException;

public class UserAlreadyExistsException extends SecurityException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
