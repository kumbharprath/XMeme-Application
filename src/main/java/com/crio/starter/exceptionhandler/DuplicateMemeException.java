package com.crio.starter.exceptionhandler;


public class DuplicateMemeException extends RuntimeException {

    public DuplicateMemeException(String message) {
        super(message);
    }
}
