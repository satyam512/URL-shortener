package com.systemdesign.urlshortener.ExceptionHandler;

public class SomeThingWentWrongException extends RuntimeException{
    public SomeThingWentWrongException(String message) {
        super(message);
    }
}