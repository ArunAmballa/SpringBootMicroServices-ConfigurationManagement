package com.eazybytes.loans.exceptions;

public class LoanAlreadyExistsException extends RuntimeException {

    public LoanAlreadyExistsException(String message) {
        super(message);
    }
    public LoanAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
